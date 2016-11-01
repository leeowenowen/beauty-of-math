package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class HeartView extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public HeartView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
  }

  /**
   * x=a*(2*cos(t)-cos(2*t))
   * y=a*(2*sin(t)-sin(2*t))
   */
  private void drawHeart(Canvas canvas) {
    mPath.reset();
    double a = 100;
    double t = 0;
    double x = 400 + a * (2 * Math.cos(t) - Math.cos(2 * t));
    double y = 400 + a * (2 * Math.sin(t) - Math.sin(2 * t));
    mPath.moveTo((float) x, (float) y);
    for (; t < 2 * Math.PI; t += 0.01) {
      x = 400 + a * (2 * Math.cos(t) - Math.cos(2 * t));
      y = 400 + a * (2 * Math.sin(t) - Math.sin(2 * t));
      mPath.lineTo((float) x, (float) y);
    }
    canvas.drawPath(mPath, mPaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawHeart(canvas);
  }
}
