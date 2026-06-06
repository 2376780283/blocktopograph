package com.mithrilmania.blocktopograph.map.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.mithrilmania.blocktopograph.WorldData;
import com.mithrilmania.blocktopograph.chunk.Chunk;
import com.mithrilmania.blocktopograph.chunk.Version;
import com.mithrilmania.blocktopograph.map.Dimension;

public class ChessPatternRenderer implements MapRenderer {

    public final int darkShade, lightShade;
    private final Bitmap chessPattern;

    ChessPatternRenderer(int darkShade, int lightShade) {
        this.darkShade = darkShade;
        this.lightShade = lightShade;
        this.chessPattern = createChessPattern();
    }

    private Bitmap createChessPattern() {
        Bitmap bitmap = Bitmap.createBitmap(16, 16, Bitmap.Config.RGB_565);
        for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
                int color = ((x + z) & 1) == 1 ? darkShade : lightShade;
                bitmap.setPixel(x, z, color);
            }
        }
        return bitmap;
    }

    @Override
    public void renderToBitmap(Chunk chunk, Canvas canvas, Dimension dimension, int chunkX, int chunkZ, int pX, int pY, int pW, int pL, Paint paint, WorldData worldData) throws Version.VersionException {
        Rect dst = new Rect(pX, pY, pX + pW * 16, pY + pL * 16);
        canvas.drawBitmap(chessPattern, null, dst, paint);
    }
}