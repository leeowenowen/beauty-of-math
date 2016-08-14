package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;


public class ArrowView extends View {
    private Paint mPaint = new Paint();
    Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Path mPath = new Path();

    public ArrowView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mMemBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
    }

//                \(r,θ)
//    (x0,y0)------(x1,y1)
//                /

    private void drawArrow(Canvas canvas) {
        double r = 10;
        double angle = Math.PI / 4;
        double x0 = 0;
        double y0 = 0;
        float x1 = 300;
        float y1 = 300;
        double x2 = (x0 - x1) - r * Math.cos(angle);
        double y2 = (y0 - y1) + r * Math.sin(angle);

        double x3 = (x0 - x1) - r * Math.cos(angle);
        double y3 = (y0 - y1) - r * Math.sin(angle);
        canvas.drawLine((float) x0, (float) y0, (float) x1, (float) y1, mPaint);
        canvas.drawLine((float) x2, (float) y2, (float) x1, (float) y1, mPaint);
        canvas.drawLine((float) x3, (float) y3, (float) x1, (float) y1, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArrow(mMemCanvas);
        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
