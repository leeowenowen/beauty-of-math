package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class Y_ax_b_View extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public Y_ax_b_View(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(2);
    mPaint.setTextSize(23.0f);
  }

  private void drawMain(Canvas canvas) {
    int width = getWidth();
    int height = getHeight();
    double offset = 0;
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

    double k = 0.8;
    int b = 50;
    mPath.reset();
    for (int x = -100; x < 100; x += 1) {
      double y = 0;
      // y = k * x + b;
      //y = -0.01 * x * x;
      y = -0.001 * x * x * x;


      int x2 = x + width / 2;
      double y2 = height / 2 - y;
      if (mPath.isEmpty()) {
        mPath.moveTo((float) x2, (float) y2);
      } else {
        mPath.lineTo((float) x2, (float) y2);
      }
    }
    canvas.drawPath(mPath, mPaint);
  }


  private void drawArrowBR(Canvas canvas,
                           double r,
                           double angle,
                           double xs,
                           double ys,
                           double xe,
                           double ye) {
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
    drawMain(canvas);
  }
}
