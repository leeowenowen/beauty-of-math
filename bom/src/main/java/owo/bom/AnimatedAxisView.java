package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.view.View;

public class AnimatedAxisView extends View {
    private Paint mPaint = new Paint();
    Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Path mPath = new Path();

    public AnimatedAxisView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(23.0f);
        mMemBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
        mHandler.postDelayed(mRunnable, 100);
    }

    private int width = 800;
    private int height = 800;

    private double offset = 100;
    private double centerX;
    private double centerY;
    private double leftX;
    private double leftY;
    private double rightX;
    private double rightY;
    private double topX;
    private double topY;
    private double bottomX;
    private double bottomY;
    private double angle;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            angle += Math.PI / 50;
            if (angle >= Math.PI) {
                angle = 0;
            }
            max += 0.05;
            if (max >= 15) {
                max = 15;
            }
            drawMain(mMemCanvas);
            mHandler.postDelayed(mRunnable, 100);
            postInvalidate();
        }
    };

    private double max = 0;

    private void drawMain(Canvas canvas) {
        centerX = width / 2 + offset;
        centerY = height / 2 + offset;
        leftX = 0 + offset;
        leftY = height / 2 + offset;
        rightX = width + offset;
        rightY = height / 2 + offset;
        topX = width / 2 + offset;
        topY = 0 + offset;
        bottomX = width / 2 + offset;
        bottomY = height + offset;
        canvas.drawColor(Color.BLACK);
        drawArrowBR(canvas, 50, angle, leftX, leftY, rightX, rightY);
        drawArrowBR(canvas, 50, angle, bottomX, bottomY, topX, topY);
        canvas.drawText("O", (float) centerX, (float) centerY, mPaint);
        canvas.drawText("Y", (float) topX, (float) topY, mPaint);
        canvas.drawText("X", (float) rightX, (float) rightY, mPaint);

        double k = 0.8;
        int b = 50;
        mPath.reset();
        for (double x = -max; x < max; x += 0.01) {
            double y = 0;
            // y = k * x + b;
            //y = -0.01 * x * x;
            y = Math.sin(x);


            double x2 = x * 20 + 500;
            double y2 = 500 - y * 40;
            if (mPath.isEmpty()) {
                mPath.moveTo((float) x2, (float) y2);
            } else {
                mPath.lineTo((float) x2, (float) y2);
            }
        }
        canvas.drawPath(mPath, mPaint);
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

        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
