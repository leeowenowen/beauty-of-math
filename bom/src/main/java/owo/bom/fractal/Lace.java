package owo.bom.fractal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by wangli on 10/31/16.
 */

public class Lace extends View {
  private Paint mPaint = new Paint();
  private int maxDepth = 16;

  public Lace(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(2);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    float cx = getWidth() / 2;
    float cy = getHeight() / 2;
    float edge = 50;
    drawLace(canvas, cx, cy, cx + edge, cy, edge, 0);
  }

  private Path mPath = new Path();

  private void drawLace(Canvas canvas,
                        float xo,
                        float yo,
                        float x1,
                        float y1,
                        float edge,
                        int depth) {
    if (depth > maxDepth) {
      return;
    }
    double alpha = Math.atan2(yo - y1, xo - x1);
    double beta = Math.PI / 3 + alpha;
    double x2 = xo + edge * Math.cos(beta);
    double y2 = yo + edge * Math.sin(beta);
    double beta2 = Math.PI / 3 - alpha;
    double x3 = xo + edge * Math.cos(beta2);
    double y3 = yo - edge * Math.sin(beta2);
//    mPath.reset();
//    mPath.moveTo((float) x2, (float) y2);
//    mPath.lineTo(xo, yo);
//    mPath.lineTo((float) x3, (float) y3);
//    canvas.drawPath(mPath, mPaint);
    canvas.drawLine((float) x2, (float) y2, xo, yo, mPaint);
    canvas.drawLine((float) x3, (float) y3, xo, yo, mPaint);
    drawLace(canvas, (float) x2, (float) y2, xo, yo, edge, depth + 1);
    drawLace(canvas, (float) x3, (float) y3, xo, yo, edge, depth + 1);
  }
}
