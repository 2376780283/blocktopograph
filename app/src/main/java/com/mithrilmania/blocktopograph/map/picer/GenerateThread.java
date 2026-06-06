package com.mithrilmania.blocktopograph.map.picer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.World;
import com.mithrilmania.blocktopograph.WorldData;
import com.mithrilmania.blocktopograph.map.Dimension;
import com.mithrilmania.blocktopograph.map.edit.RectEditTarget;
import com.mithrilmania.blocktopograph.map.renderer.MapRenderer;
import com.mithrilmania.blocktopograph.util.UiUtil;

import java.lang.ref.WeakReference;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class GenerateThread extends Thread {

    private static final long UPDATE_INTERVAL_MS = 50;
    private static final int POOL_SIZE = 5;

    private final Rect area;
    private final WeakReference<PicerFragment> owner;
    private final int scale;
    private final Bitmap.Config config;
    private final WeakReference<AlertDialog> dialogRef;
    private final AtomicInteger processedChunks = new AtomicInteger(0);
    private final int totalChunks;
    private volatile boolean cancelled;

    GenerateThread(PicerFragment owner, Rect area, int scale, Bitmap.Config config,
                   AlertDialog dialog) {
        this.owner = new WeakReference<>(owner);
        this.area = area;
        this.scale = scale;
        this.config = config;
        this.dialogRef = new WeakReference<>(dialog);

        int chunkW = (area.right - area.left + 16) / 16;
        int chunkH = (area.bottom - area.top + 16) / 16;
        this.totalChunks = Math.max(chunkW * chunkH, 1);
    }

    @Override
    public void run() {
        PicerFragment owner = this.owner.get();
        if (owner == null) return;

        Dimension dimension = owner.mDimension;
        MapRenderer renderer = dimension.defaultMapType.renderer;
        WorldData wdata = owner.mWorld.getWorldData();

//        int width = area.right - area.left + 1;
        int width = area.right - area.left;

//        int height = area.bottom - area.top + 1;
        int height = area.bottom - area.top;

        Bitmap bitmap = Bitmap.createBitmap(width * scale, height * scale, config);
        Log.d(this,"Create bitmap: "+bitmap.getWidth()+"x"+bitmap.getHeight());
        Canvas canvas = new Canvas(bitmap);

        ExecutorService executor = new ThreadPoolExecutor(
                POOL_SIZE, POOL_SIZE, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(POOL_SIZE << 1),
                new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadLocal<Paint> paintSub = new ThreadLocal<Paint>() {
            @NonNull
            @Override
            protected Paint initialValue() {
                return new Paint();
            }
        };

        Handler uiHandler = new Handler(Looper.getMainLooper());
        Runnable progressTask = new Runnable() {
            @Override
            public void run() {
                if (cancelled) return;
                int current = processedChunks.get();
                int progress = Math.min((int) ((current * 1000L) / totalChunks), 1000);

                UiUtil.updateProgress(dialogRef.get(), progress);
                uiHandler.postDelayed(this, UPDATE_INTERVAL_MS);
            }
        };
        uiHandler.post(progressTask);
        owner.mWorld.setHaveBackgroundJob(this,true);

        new RectEditTarget(wdata, area, dimension).forEachChunk(
                (chunk, fromX, toX, fromY, toY, fromZ, toZ) -> {
                    if (cancelled) {
                        executor.shutdownNow();
                        return 0;
                    }
                    executor.execute(() -> {
                        try {
                            renderer.renderToBitmap(chunk, canvas, dimension,
                                    chunk.mChunkX, chunk.mChunkZ,
                                    (chunk.mChunkX * 16 - area.left) * scale,
                                    (chunk.mChunkZ * 16 - area.top) * scale,
                                    scale, scale, paintSub.get(), wdata);
                        } catch (Exception e) {
                            Log.d(this, e);
                        } finally {
                            processedChunks.incrementAndGet();
                        }
                    });
                    return 0;
                });

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // ignore
        }

        World world = owner.mWorld;
        cancelled = true;
        uiHandler.removeCallbacks(progressTask);
        UiUtil.updateProgress(dialogRef.get(), 1000);

        owner = this.owner.get();
        if (owner == null) return;

        FragmentActivity activity = owner.getActivity();
        if (activity != null) {
            activity.runOnUiThread(() -> {
                AlertDialog dialog = dialogRef.get();
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                UiUtil.releaseDialog(dialog);
                world.setHaveBackgroundJob(this,false);
                PicerFragment fragment = this.owner.get();
                if (fragment != null) {
                    fragment.onGenerationDone(bitmap, dialog);
                }
            });
        } else {
            owner.mWorld.setHaveBackgroundJob(this,false);
        }


        owner.mOngoingThread = null;
    }

    void cancel() {
        cancelled = true;
        interrupt();
        PicerFragment owner = this.owner.get();
        if (owner != null) {
            FragmentActivity activity = owner.getActivity();
            if (activity != null) {
                activity.runOnUiThread(() -> owner.mWorld.setHaveBackgroundJob(this,false));
            } else {
                owner.mWorld.setHaveBackgroundJob(this,false);
            }
        }
    }
}