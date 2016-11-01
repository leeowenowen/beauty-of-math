package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;


public class CloverView extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public CloverView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
  }

  /**
   * x=sin(t)*sin(t)*cos(t)
   * y=cos(t)*cos(t)*sin(t)
   */
  private void drawHeart(Canvas canvas) {
    mPath.reset();
    double a = 400;
    double t = 0;
    double x = 400 + a * Math.sin(t) * Math.sin(t) * Math.cos(t);
    double y = 400 + a * Math.cos(t) * Math.cos(t) * Math.sin(t);
    mPath.moveTo((float) x, (float) y);
    for (; t < 2 * Math.PI; t += 0.01) {
      x = 400 + a * Math.sin(t) * Math.sin(t) * Math.cos(t);
      y = 400 + a * Math.cos(t) * Math.cos(t) * Math.sin(t);
      mPath.lineTo((float) x, (float) y);
      Log.d("XXX", "" + x + ":" + y);
    }
    canvas.drawPath(mPath, mPaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawHeart(canvas);
  }
}
