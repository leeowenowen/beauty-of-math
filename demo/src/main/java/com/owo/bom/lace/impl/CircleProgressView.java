package com.owo.bom.lace.impl;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import owo.bom.drawer.CircleBezierDrawerInscribe;

/**
 * Created by wangli on 11/3/16.
 */

public class CircleProgressView extends View {
  CircleBezierDrawerInscribe drawer = new CircleBezierDrawerInscribe();
  private Paint mPaint = new Paint();
  private double angle = 0;
  int n = 10;
  int R = 400;
  int r1 = 20;
  int r2 = 30;
  double detal;

  public CircleProgressView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    detal = Math.PI * 2 / n;
    postDelayed(new Runnable() {
      @Override
      public void run() {
        angle += 0.1;
        if (angle > Math.PI * 2) {
          angle = 0;
          return;
        }
        postInvalidate();

        postDelayed(this, 200);
      }
    }, 200);
  }

  int compute() {
    for (int i = 0; i < n - 1; ++i) {
      if (i * detal <= angle && (i + 1) * detal > angle) {
        return i;
      }
    }
    return 0;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Camera camera = new Camera();
    camera.rotate(20,20,20);
    camera.applyToCanvas(canvas);
    float centerX = getWidth() / 2;
    float centerY = getHeight() / 2;
    Log.d("xxx", "onDraw---------------------------");
    int k = compute();
    float xK = (float) (R * Math.cos(angle)) + centerX;
    float yK = (float) (R * Math.sin(angle)) + centerY;
    for (int i = 0; i < n; ++i) {
      double tmpAngle = i * detal;
      float x = (float) (R * Math.cos(tmpAngle)) + centerX;
      float y = (float) (R * Math.sin(tmpAngle)) + centerY;
      canvas.drawCircle(x, y, r2, mPaint);

      if ((k == i || k == i - 1) && Math.abs(angle - tmpAngle) < detal / 2) {
        Log.d("xxx", "k:" + k + " i:" + i + " angle:" + angle + " tmpAngle:" + tmpAngle);
        drawer.r(r1).R(r2).start(x, y).end(xK, yK).withEndCircle().draw(canvas, mPaint);
      }
    }
  }
}
