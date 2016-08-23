package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

public class BezierView extends View {
    private Paint mPaint = new Paint();
    Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Path mPath = new Path();

    public BezierView(Context context) {
        super(context);


        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(23.0f);
        mMemBitmap = Bitmap.createBitmap(1100, 1000, Bitmap.Config.ARGB_8888);
        int c10 = c(1, 0);
        int c11 = c(1, 1);
        int c20 = c(2, 0);
        int c21 = c(2, 1);
        int c22 = c(2, 2);
        int c30 = c(3, 0);
        int c31 = c(3, 1);
        int c32 = c(3, 2);
        int c33 = c(3, 3);
        double i1 = Math.pow(2, 1);
        double i2 = Math.pow(2, 2);
        double i3 = Math.pow(2, 4);
        mMemCanvas = new Canvas(mMemBitmap);
    }

    private int width = 800;
    private int height = 800;

    private void drawMain(Canvas canvas) {
        double offset = 100;
        double centerX = width / 2 + offset;
        double centerY = height / 2 + offset;
        double leftX = 0 + offset;
        double leftY = height / 2 + offset;
        double rightX = width + offset;
        double rightY = height / 2 + offset;
        double topX = width / 2 + offset;
        double topY = 0 + offset;
        double bottomX = width / 2 + offset;
        double bottomY = height + offset;
        double angle = Math.PI / 4;

        drawArrowBR(canvas, 50, angle, leftX, leftY, rightX, rightY);
        drawArrowBR(canvas, 50, angle, bottomX, bottomY, topX, topY);
        canvas.drawText("O", (float) centerX, (float) centerY, mPaint);
        canvas.drawText("Y", (float) topX, (float) topY, mPaint);
        canvas.drawText("X", (float) rightX, (float) rightY, mPaint);


        mPath.reset();

        int n = 4;
        Point[] pts = new Point[]{
                new Point(50, 20),
                new Point(10, 180),
                new Point(200, 200),
                new Point(250, 30),
                new Point(300, 200),
        };

        drawLine(canvas, pts[0], pts[1]);
        drawLine(canvas, pts[1], pts[2]);
        drawLine(canvas, pts[2], pts[3]);
        drawLine(canvas, pts[3], pts[4]);
        for (double t = 0; t <= 1; t += 0.01) {
            double x = 0.0;
            double y = 0.0;
            for (int i = 0; i < n; ++i) {
                x += c(n, i) * pts[i].x * Math.pow(1 - t, n - i) * Math.pow(t, i);
                y += c(n, i) * pts[i].y * Math.pow(1 - t, n - i) * Math.pow(t, i);
                Log.d("__", "" + x + " " + y);

            }
            Log.d("xx", "a:" + t + " " + x + " " + y);
            x += 500;
            y = 500 - y;
            Log.d("xx", "b:" + t + " " + x + " " + y);
            if (mPath.isEmpty()) {
                mPath.moveTo((float) x, (float) y);
            } else {
                mPath.lineTo((float) x, (float) y);
            }
        }
        canvas.drawPath(mPath, mPaint);
    }

    private void drawLine(Canvas canvas, Point s, Point e) {
        canvas.drawLine(s.x + 500, 500 - s.y, e.x + 500, 500 - e.y, mPaint);
    }


    private int c(int n, int i) {
        return jiecheng(n) / (jiecheng(n - i) * jiecheng(i));
    }

    private int jiecheng(int n) {
        if (n == 0) {
            return 1;
        }
        return n * jiecheng(n - 1);
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
        drawMain(mMemCanvas);
        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
