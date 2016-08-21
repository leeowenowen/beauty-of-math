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
        double r = 50;
        double angle = Math.PI / 8;
        drawArrow(canvas, r, angle, 200, 200, 100, 100);
        drawArrow(canvas, r, angle, 200, 200, 300, 300);
        drawArrow(canvas, r, angle, 200, 200, 100, 300);
        drawArrow(canvas, r, angle, 200, 200, 300, 100);
    }

    private void drawArrow(Canvas canvas, double r, double angle, double xs, double ys, double xe, double ye) {
        if (xe > xs) {
            if (ye > ys) {
                //direction BR draw TR
                drawArrowBR(canvas, r, angle, xs, ys, xe, ye);
            } else {
                //direction TR, draw BR
                drawArrowBR(canvas, r, angle, xs, ys, xe, ye);
            }
        } else {
            if (ye > ys) {
                //direction TR, draw BR
                drawArrowBR(canvas, r, angle, xs, ys, xe, ye);
            } else {
                //direction BR, draw TR
                drawArrowBR(canvas, r, angle, xs, ys, xe, ye);
            }
        }
    }

    private void drawArrowBR(Canvas canvas, double r, double angle, double xs, double ys, double xe, double ye) {
        double angle2 = Math.atan2(ye - ys, xe - xs);
        angle2 = -angle2;
        double x2 = xe - r * Math.cos(angle + angle2);
        double y2 = ye + r * Math.sin(angle + angle2);

        double x3 = xe - r * Math.cos(angle2 - angle);
        double y3 = ye + r * Math.sin(angle2 - angle);

        drawLine(canvas, xs, ys, xe, ye, mPaint);
        drawLine(canvas, x2, y2, xe, ye, mPaint);
        drawLine(canvas, x3, y3, xe, ye, mPaint);
    }


    private void drawLine(Canvas canvas, double x1, double y1, double x2, double y2, Paint paint) {
        x1 = Math.abs(x1);
        y1 = Math.abs(y1);
        x2 = Math.abs(x2);
        y2 = Math.abs(y2);
        canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArrow(mMemCanvas);
        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
