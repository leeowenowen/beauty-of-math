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

public class RectDecorView extends View {
  public RectDecorView(Context context) {
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
    int interval = 20;
    int half_interval = interval / 2;
    int margin = 20;
    mPath.reset();
    //top
    mPath.moveTo(0, 0);
    int cur = 0;
    while (cur < getRight()) {
      mPath.lineTo(cur + half_interval, margin);
      mPath.lineTo(cur + interval, 0);
      cur += interval;
    }
    //left
    cur = 0;
    mPath.moveTo(0, 0);
    while (cur < getBottom()) {
      mPath.lineTo(margin, cur + half_interval);
      mPath.lineTo(0, cur + interval);
      cur += interval;
    }
    //bottom
    cur = 0;
    mPath.moveTo(0, getBottom());
    while (cur < getRight()) {
      mPath.lineTo(cur + half_interval, getBottom() - margin);
      mPath.lineTo(cur + interval, getBottom());
      cur += interval;
    }
    //right
    cur = 0;
    mPath.moveTo(getRight(), getTop());
    while (cur < getBottom()) {
      mPath.lineTo(getRight() - margin, cur + half_interval);
      mPath.lineTo(getRight(), cur + interval);
      cur += interval;
    }

    canvas.drawPath(mPath, mPaint);
  }
}
