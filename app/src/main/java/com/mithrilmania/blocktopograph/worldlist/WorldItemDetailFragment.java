package com.mithrilmania.blocktopograph.worldlist;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.mithrilmania.blocktopograph.BackupActivity;
import com.mithrilmania.blocktopograph.Log;
import com.mithrilmania.blocktopograph.R;
import com.mithrilmania.blocktopograph.World;
import com.mithrilmania.blocktopograph.WorldActivity;
import com.mithrilmania.blocktopograph.WorldData;
import com.mithrilmania.blocktopograph.backup.WorldBackups;
import com.mithrilmania.blocktopograph.databinding.WorlditemDetailBinding;
import com.mithrilmania.blocktopograph.test.MainTestActivity;
import com.mithrilmania.blocktopograph.util.IoUtil;
import com.mithrilmania.blocktopograph.util.UiUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Date;

/**
 * A fragment representing a single WorldItem detail screen.
 * This fragment is either contained in a {@link WorldItemListActivity}
 * in two-pane mode (on tablets) or a {@link WorldItemDetailActivity}
 * on handsets.
 */
public class WorldItemDetailFragment extends Fragment implements View.OnClickListener {

    /**
     * The dummy content this fragment is presenting.
     */
    private static final byte[] SEQ_TEST = {0, 1, 1, 0};

    private World mWorld;
    private byte[] mSequence;
    Activity activity;
    AlertDialog waitDialog;
    long totalSize;
    public static final String TIP_DONT_EDIT_WITH_PLAY = "tip_edit_warn";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WorldItemDetailFragment() {
    }

//    private String getDate(long time) {
//        Calendar cal = Calendar.getInstance();
//        TimeZone tz = cal.getTimeZone();//get your local time zone.
//        SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.full_date_format), Locale.ENGLISH);
//        sdf.setTimeZone(tz);//set time zone.
//        return sdf.format(new Date(time * 1000));
//    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WorlditemDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.worlditem_detail, container, false);

        activity = this.getActivity();
        assert activity != null;

        SharedPreferences prefs = activity.getPreferences(MODE_PRIVATE);
        if (prefs.getInt(TIP_DONT_EDIT_WITH_PLAY, 0) == 1) {

        }else{
            new AlertDialog.Builder(activity)
                    .setTitle("Tips:")
                    .setMessage(R.string.tip_dont_edit_with_play)
                    .setPositiveButton(R.string.general_got_it,(dialog, which) -> {
                        dialog.dismiss();
                    })
                    .setNegativeButton(R.string.dont_show_again_btn, (dialog, which) -> {
                        dialog.dismiss();
                        prefs.edit().putInt(TIP_DONT_EDIT_WITH_PLAY, 1).apply();
                    })
                    .setNeutralButton(R.string.open_auto_backup_page,((dialogInterface, i) -> {
                        Intent intent = new Intent(getActivity(), BackupActivity.class).putExtra(World.ARG_WORLD_SERIALIZED, mWorld);
                        startActivity(intent);
                        dialogInterface.dismiss();

                    }))
                    .setCancelable(false)
                    .show();

        }

        String barTitle;
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey(World.ARG_WORLD_SERIALIZED)) {
            Snackbar.make(binding.worlditemDetail,
                    R.string.error_could_not_open_world_details_lost_track_world, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            barTitle = activity.getString(R.string.error_could_not_open_world);
        } else {
            mWorld = (World) arguments.getSerializable(World.ARG_WORLD_SERIALIZED);
            barTitle = mWorld == null ? activity.getString(R.string.error_could_not_open_world) : mWorld.getWorldDisplayName();
        }

        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(barTitle);
        }

        try {
            if (mWorld != null && mWorld.getLevel() != null) {
                binding.setName(mWorld.getWorldDisplayName());
                binding.setSize(IoUtil.getFileSizeInText(FileUtils.sizeOf(mWorld.worldFolder)));
                binding.setMode(WorldListUtil.getWorldGamemodeText(activity, mWorld));
                binding.setTime(WorldListUtil.getLastPlayedText(activity, mWorld));
                binding.setSeed(String.valueOf(mWorld.getWorldSeed()));
                binding.setPath(mWorld.levelFile.getAbsolutePath());
            }
        } catch (Exception e) {
            Log.d(this, e);
        }

        binding.fabOpenWorld.setOnClickListener(view -> {
            boolean dbvalid = true;
            try {
                mWorld.getWorldData().load();
                mWorld.getWorldData().openDB();
            } catch (Exception e) {
                dbvalid = false;
                String msg = e.getMessage();
                if(msg.contains("Permission denied")){
                    Toast.makeText(activity,R.string.db_permission_denied,Toast.LENGTH_SHORT).show();
                    onDBOccupied();
                }else if(msg.contains("IO error: lock")){
                    Toast.makeText(activity,R.string.db_occupied,Toast.LENGTH_SHORT).show();

                }
                e.printStackTrace();
            } finally {
                try {
                    mWorld.getWorldData().closeDB();
                } catch (WorldData.WorldDBException e) {
                    throw new RuntimeException(e);
                }
            }
            if(dbvalid){
                WorldBackups worldBackups = new WorldBackups(mWorld);
//                if(!worldBackups.hasDir()){
//                    worldBackups.setAutoBackup(true,null);
//                    worldBackups.saveConfig();
//                }
                worldBackups.loadConfig();
                if (worldBackups.autoBackup) {
                    AlertDialog dia = UiUtil.buildProgressWaitDialog(view.getContext(),
                            R.string.auto_backup_caption, null);
                    dia.show();
                    new AsyncTask<WorldBackups, Void, Boolean>() {
                        @Override
                        protected Boolean doInBackground(WorldBackups... worldBackups) {
                            return worldBackups[0].createNewBackup(getString(R.string.auto_backup_name), new Date());
                        }

                        @Override
                        protected void onPostExecute(Boolean aBoolean) {
                            if (!aBoolean) {
                                Activity activity1 = getActivity();
                                if (activity1 != null)
                                    UiUtil.snack(activity1, R.string.general_failed);
                            }
                            synchronized (dia) {
                                if (dia.isShowing()) dia.dismiss();
                            }
                            startWorldActivity();
                        }
                    }.execute(worldBackups);
                } else startWorldActivity();
            }

        });

        mSequence = new byte[]{-1, -1, -1, -1};
        binding.buttonLeft.setOnClickListener(this);
        binding.buttonRight.setOnClickListener(this);
        binding.buttonBackup.setOnClickListener(this);

        return binding.getRoot();
    }
    private void onDBOccupied(){
        if(checkParentFolderWriteable()){
            waitDialog = UiUtil.buildProgressWaitDialog(activity, R.string.picer_progress_analyzing,null);
            waitDialog.show();
            new Thread(() -> {
                long folderSize = calculateDirSize(mWorld.worldFolder);
                totalSize = folderSize;
                activity.runOnUiThread(() -> {
                    waitDialog.dismiss();
                    new AlertDialog.Builder(activity)
                            .setTitle(R.string.no_permission)
                            .setMessage(getString(R.string.ask_copy_world_folder,formatFileSize(folderSize)))
                            .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                dialog.dismiss();
                                new Thread(this::copyWorldFolder).start();

                            })
                            .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                            .setCancelable(false)
                            .show();
                });
            }).start();

        }else{
            waitDialog = UiUtil.buildProgressWaitDialog(activity, R.string.picer_progress_analyzing,null);
            waitDialog.show();
            new Thread(() -> {
                long folderSize = calculateDirSize(mWorld.worldFolder.getParentFile());
                totalSize = folderSize;
                activity.runOnUiThread(() -> {
                    waitDialog.dismiss();
                    new AlertDialog.Builder(activity)
                            .setTitle(R.string.no_permission)
                            .setMessage(getString(R.string.ask_copy_game_folder,formatFileSize(folderSize)))
                            .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                dialog.dismiss();
                                new Thread(this::moveAllFiles).start();

                            })
                            .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                            .setCancelable(false)
                            .show();
                });
            }).start();

        }
    }
    private boolean checkParentFolderWriteable(){
        File parentFLoder = mWorld.worldFolder.getParentFile();
        File testFile = null;
        try {
            testFile = new File(parentFLoder, ".write_test_" + System.currentTimeMillis());
            if (testFile.createNewFile()) {
                testFile.delete();
            }
        } catch (IOException e) {
            Log.d(this,"Directory not writable: " + parentFLoder.getAbsolutePath());
            return false;
        } finally {
            if (testFile != null && testFile.exists()) {
                testFile.delete();
            }

        }
        return true;
    }
    private void moveAllFiles() {
        File sourceDir = mWorld.worldFolder.getParentFile().getParentFile().getParentFile().getParentFile();
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            activity.runOnUiThread(() ->
                    Toast.makeText(activity, R.string.source_folder_not_exist, Toast.LENGTH_SHORT).show()
            );
            return;
        }

        if(totalSize == 0){
            totalSize = calculateDirSize(sourceDir);
        }
        long threshold = 300L * 1024 * 1024; // 300MB

        if (totalSize > threshold) {
            activity.runOnUiThread(() -> {
                String sizeStr = formatFileSize(totalSize);
                new AlertDialog.Builder(activity)
                        .setTitle(R.string.big_files)
                        .setMessage(getString(R.string.ask_copy_large_files,sizeStr))
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            dialog.dismiss();
                            new Thread(() -> performMoveOperation(sourceDir)).start();
                        })
                        .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                        .setCancelable(false)
                        .show();
            });
        } else {
            new Thread(() -> performMoveOperation(sourceDir)).start();
        }
    }
    private void copyWorldFolder() {
        File worldFolder = mWorld.worldFolder;
        if (worldFolder == null || !worldFolder.exists() || !worldFolder.isDirectory()) {
            Toast.makeText(activity, R.string.world_folder_not_exist, Toast.LENGTH_SHORT).show();
            return;
        }

        File parentDir = worldFolder.getParentFile();
        String copyFolderName = worldFolder.getName() + "_copy";
        File targetFolder = new File(parentDir, copyFolderName);

        if (targetFolder.exists()) {
//            deleteDirectory(targetFolder);
            activity.runOnUiThread(() ->{
                new AlertDialog.Builder(activity)
                        .setTitle(R.string.world_duplication)
                        .setMessage(getString(R.string.world_copied_info,targetFolder.getName()))
                        .setPositiveButton(R.string.general_got_it, (dialog, which) -> {
                            dialog.dismiss();
                            activity.finish();
                        })
                        .setCancelable(false)
                        .show();
            });

        }else{
            activity.runOnUiThread(() -> {
                waitDialog = UiUtil.buildProgressWaitDialog(
                        activity,
                        R.string.picer_progress_copying,
                        null
                );
                waitDialog.show();
            });

            new Thread(() -> {
                try {
                    copyDirectory(worldFolder, targetFolder);

                    activity.runOnUiThread(() -> {
                        if (waitDialog != null && waitDialog.isShowing()) {
                            waitDialog.dismiss();
                        }

                        new AlertDialog.Builder(activity)
                                .setTitle(R.string.copy_complete)
                                .setMessage(getString(R.string.copy_complete_info,targetFolder.getName()))
                                .setPositiveButton(R.string.general_got_it, (dialog, which) -> {
                                    dialog.dismiss();
                                    activity.finish();
                                })
                                .setCancelable(false)
                                .show();
                    });

                } catch (Exception e) {
                    e.printStackTrace();

                    activity.runOnUiThread(() -> {
                        if (waitDialog != null && waitDialog.isShowing()) {
                            waitDialog.dismiss();
                        }

                        Toast.makeText(
                                activity,
                                getString(R.string.copy_failed) + e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    });
                }
            }).start();
        }
    }
    private void performMoveOperation(File sourceDir) {
        File parentDir = sourceDir.getParentFile();
        File tempDir = new File(parentDir, "files_temp");
        File backupDir = new File(parentDir, "files_backup");
        activity.runOnUiThread(() -> {
            waitDialog = UiUtil.buildProgressWaitDialog(activity, R.string.picer_progress_copying, null);
            waitDialog.show();
        });
        try {
            if (tempDir.exists()) {
                deleteDirectory(tempDir);
            }
            if (!tempDir.mkdirs()) {
                throw new IOException("无法创建临时目录: " + tempDir.getAbsolutePath());
            }

            copyDirectory(sourceDir, tempDir);

            if (!sourceDir.renameTo(backupDir)) {
                throw new IOException("无法重命名源目录为 files_backup");
            }

            if (!tempDir.renameTo(sourceDir)) {
                backupDir.renameTo(sourceDir);
                throw new IOException("无法重命名临时目录为 files");
            }


            activity.runOnUiThread(() -> {
                waitDialog.dismiss();
                new AlertDialog.Builder(activity)
                        .setTitle(R.string.copy_complete)
                        .setMessage(R.string.copy_game_folder_complete_info)
                        .setPositiveButton(R.string.general_got_it, (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setCancelable(false)
                        .show();
            });

        } catch (Exception e) {
            e.printStackTrace();
            if (tempDir.exists()) {
                deleteDirectory(tempDir);
            }

            activity.runOnUiThread(() -> {
                waitDialog.dismiss();
                Toast.makeText(activity, getString(R.string.general_failed) + e.getMessage(), Toast.LENGTH_LONG).show();
            });

        }
    }

    private long calculateDirSize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0;

        for (File file : files) {
            if (file.isDirectory()) {
                size += calculateDirSize(file);
            } else {
                size += file.length();
            }
        }
        return size;
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (source.isDirectory()) {
            if (!target.exists() && !target.mkdirs()) {
                throw new IOException("无法创建目录: " + target.getAbsolutePath());
            }

            File[] files = source.listFiles();
            if (files == null) return;

            for (File file : files) {
                File destFile = new File(target, file.getName());
                copyDirectory(file, destFile);
            }
        } else {
            copyFile(source, target);
        }
    }

    private void copyFile(File source, File target) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(target).getChannel()) {

            long size = sourceChannel.size();
            long position = 0;

            while (position < size) {
                position += sourceChannel.transferTo(position, size - position, targetChannel);
            }
        }
    }

    private boolean deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return dir.delete();
    }

    private String formatFileSize(long size) {
        if (size < 1024) return size + " B";
        int exp = (int) (Math.log(size) / Math.log(1024));
        char unit = "KMGTPE".charAt(exp - 1);
        return String.format("%.2f %sB", size / Math.pow(1024, exp), unit);
    }
    private void startWorldActivity() {

        Activity activity = getActivity();
        assert activity != null;
        activity.startActivity(
                new Intent(activity, WorldActivity.class)
                        .putExtra(World.ARG_WORLD_SERIALIZED, mWorld));
    }

    private void sequence(byte code) {
        int pos = mSequence.length - 1;
        System.arraycopy(mSequence, 1, mSequence, 0, pos);
        mSequence[pos] = code;
        if (Arrays.equals(mSequence, SEQ_TEST)) {
            startActivity(
                    new Intent(getActivity(), MainTestActivity.class)
                            .putExtra(World.ARG_WORLD_SERIALIZED, mWorld)
            );
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left:
                sequence((byte) 0);
                break;
            case R.id.button_right:
                sequence((byte) 1);
                break;
            case R.id.button_backup:
                startActivity(new Intent(getActivity(), BackupActivity.class).putExtra(World.ARG_WORLD_SERIALIZED, mWorld));
                break;
        }
    }
}

