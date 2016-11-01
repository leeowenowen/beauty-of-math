package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class AxisView extends View {
  private Paint mPaint = new Paint();

  public AxisView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
    mPaint.setTextSize(23.0f);
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
