package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;


public class LinearFunctionView extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public LinearFunctionView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  /**
   * y = kx + b
   */
  private void drawLine(Canvas canvas) {
    double k = 4;
    double b = 20;
    double x0 = -100;
    double x1 = 100;
    double y0 = k * x0 + b;
    double y1 = k * x1 + b;
    canvas.drawLine((float) x0, (float) x1, (float) y0, (float) y1, mPaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawLine(canvas);
  }
}
