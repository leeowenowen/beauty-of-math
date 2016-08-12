package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;


public class SpiralView extends View {
    private Paint mPaint = new Paint();
    Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Path mPath = new Path();

    public SpiralView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mMemBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
    }

    /**
     * x=sin(t)*sin(t)*cos(t)
     * y=cos(t)*cos(t)*sin(t)
     */
    private void drawHeart(Canvas canvas) {
        mPath.reset();
        double a = 10;
        double t = 0;
        double x = 400 + a * t * Math.cos(t);
        double y = 400 + a * t * Math.sin(t);
        mPath.moveTo((float) x, (float) y);
        for (; t < 10 * Math.PI; t += 0.01) {
            x = 400 + a * t * Math.cos(t);
            y = 400 + a * t * Math.sin(t);
            mPath.lineTo((float) x, (float) y);
            Log.d("XXX", "" + x + ":" + y);
        }
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHeart(mMemCanvas);
        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
