package com.owo.bom.lace.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;


public class Spiral2View extends View {
  private Paint mPaint = new Paint();
  private Path mPath = new Path();

  public Spiral2View(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(2);
    mPaint.setStyle(Paint.Style.STROKE);
  }

  /**
   * x=sin(t)*sin(t)*cos(t)
   * y=cos(t)*cos(t)*sin(t)
   */

  private double rad(double t) {
    //  return (180 * t / Math.PI);
    return t;
  }
//渐开线
  private void drawMain(Canvas canvas) {
    int w = getWidth();
    int h = getHeight();
    double r = 0.1;
    double t = 0;
    double prex = w / 2 + r * (Math.cos(t) + rad(t) * Math.sin(t));
    double prey = h / 2 + r * (Math.sin(t) - rad(t) * Math.cos(t));
    double x, y;
    double factor = 1;
    for (; t < 20 * Math.PI; t += 0.1) {
      factor = factor * 1.007;
      x = w / 2 + factor * r * (Math.cos(t) + rad(t) * Math.sin(t));
      y = h / 2 + factor * r * (Math.sin(t) - rad(t) * Math.cos(t));
      canvas.drawLine((float) prex, (float) prey, (float) x, (float) y, mPaint);
      prex = x;
      prey = y;

      Log.d("XXX", "" + x + ":" + y);
    }
    //    canvas.drawPath(mPath, mPaint);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawMain(canvas);
  }
}
