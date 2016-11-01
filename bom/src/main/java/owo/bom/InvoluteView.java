package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;


public class InvoluteView extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public InvoluteView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  double cita(double alpha) {
    return Math.tan(alpha) - alpha;
  }

  /**
   * x=r×cos(θ+α)+(θ+α)×r×sin(θ+α)
   * y=r×sin(θ+α)-(θ+α)×r×cos(θ+α)
   */
  private void drawHeart(Canvas canvas) {
    mPath.reset();
    double r = 10;
    double t = 0;
    double angle = cita(t) + t;
    double x = getWidth() / 3 + r * Math.cos(angle) + angle * r * Math.sin(angle);
    double y = getHeight() / 3 + r * Math.sin(angle) - angle * r * Math.cos(angle);
    mPath.moveTo((float) x, (float) y);
    for (; t < 2 * Math.PI; t += 0.001) {
      angle = cita(t) + t;
      x = getWidth() / 3 + r * Math.cos(angle) + angle * r * Math.sin(angle);
      y = getHeight() / 3 + r * Math.sin(angle) - angle * r * Math.cos(angle);
      //mPath.lineTo((float) x, (float) y);
      canvas.drawPoint((float) x, (float) y, mPaint);
    }
    //canvas.drawPath(mPath, mPaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawHeart(canvas);
  }
}
