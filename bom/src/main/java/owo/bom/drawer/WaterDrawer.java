package owo.bom.drawer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import owo.bom.splash.BaseDrawer;

/**
 * Created by wangli on 16-9-28.
 */
public class WaterDrawer extends BaseDrawer {
    private int xOffset;

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }


    private Path mPath = new Path();

    @Override
    public void draw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        Matrix matrix = new Matrix();
        float ratio = 1.2f;
        for (float i = 0; i < 360; i += 2) {
            matrix.setRotate(i);
            matrix.preTranslate(-500, -500);
            matrix.postTranslate(500, 500);
            float[] pts = new float[]{500, 500, (float) (600 + xOffset), 0, (float) (800 + xOffset * 1.2), 700, (float) (1000 + xOffset * 1.2 * 1.2), 500};
            matrix.mapPoints(pts);


            mPath.reset();
//            mPath.moveTo(0, 500);
//            mPath.cubicTo(100 + xOffset, 100, 400 + xOffset, 1000, 800 + xOffset, 500);
            mPath.moveTo(pts[0], pts[1]);
            mPath.cubicTo(pts[2], pts[3], pts[4], pts[5], pts[6], pts[7]);
            canvas.drawPath(mPath, mPaint);
        }
    }
}
