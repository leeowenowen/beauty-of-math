package owo.bom.decor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by wangli on 10/21/16.
 */

public class BezierDecorView extends View {
  public BezierDecorView(Context context) {
    super(context);
    mPaint.setAntiAlias(true);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  Paint mPaint = new Paint();
  Path mPath = new Path();

  private double prerx1;
  private double prery1;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    int ox = getWidth() / 2;
    int oy = getHeight() / 2;

    mPath.reset();
    double step_angle = Math.PI / 20;
    int R = 400;
    int r = 300;
    for (int i = 0; i < 100; i++) {
      double angle = i * step_angle;
      double Rx1 = ox + R * Math.cos(angle);
      double Ry1 = oy + R * Math.sin(angle);

      double rx1 = ox + r * Math.cos(angle);
      double ry1 = oy + r * Math.sin(angle);
      if (mPath.isEmpty()) {
        mPath.moveTo((float) Rx1, (float) Ry1);
      } else {
        double rmidx = (rx1 + prerx1) / 2;
        double rmidy = (ry1 + prery1) / 2;
        mPath.quadTo((float) rmidx, (float) rmidy, (float) Rx1, (float) Ry1);
      }
      prerx1 = rx1;
      prery1 = ry1;
    }

    canvas.drawPath(mPath, mPaint);
  }
}
