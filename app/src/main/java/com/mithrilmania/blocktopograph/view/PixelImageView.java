package com.mithrilmania.blocktopograph.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class PixelImageView extends androidx.appcompat.widget.AppCompatImageView {

    private Paint paint;

    public PixelImageView(Context context) {
        super(context);
        init();
    }

    public PixelImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setFilterBitmap(false);
        paint.setDither(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable && paint != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                Rect bounds = drawable.getBounds();
                if (bounds.isEmpty()) {
                    bounds.set(0, 0, getWidth(), getHeight());
                }

                float scaleX = (float) getWidth() / bitmap.getWidth();
                float scaleY = (float) getHeight() / bitmap.getHeight();
                float scale = Math.min(scaleX, scaleY);

                float scaledW = bitmap.getWidth() * scale;
                float scaledH = bitmap.getHeight() * scale;
                float left = (getWidth() - scaledW) / 2f;
                float top = (getHeight() - scaledH) / 2f;

                RectF dst = new RectF(left, top, left + scaledW, top + scaledH);

                canvas.drawBitmap(bitmap, null, dst, paint);
                return;
            }
        }
        super.onDraw(canvas);
    }
}