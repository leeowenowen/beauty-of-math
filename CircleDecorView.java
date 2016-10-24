package oob.owo.com.webviewadtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by wangli on 10/21/16.
 */

public class CircleDecorView extends View {
  public CircleDecorView(Context context) {
    super(context);
    mPaint.setAntiAlias(true);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  Paint mPaint = new Paint();
  Path mPath = new Path();

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    int ox = getWidth() / 2;
    int oy = getHeight() / 2;

    mPath.reset();
    int step_angle = 10;
    int R = 400;
    int r = 300;
    for (int i = 0; i < 100; i++) {
      int angle = i * step_angle;
      double Rx1 = ox + R * Math.cos(angle);
      double Ry1 = oy + R * Math.sin(angle);

      double Rx2 = ox + R * Math.cos(angle + step_angle);
      double Ry2 = oy + R * Math.sin(angle + step_angle);

      double rx1 = ox + r * Math.cos(angle);
      double ry1 = oy + r * Math.sin(angle);
      if(mPath.isEmpty())
      {

      }
    }

    canvas.drawPath(mPath, mPaint);
  }
}
