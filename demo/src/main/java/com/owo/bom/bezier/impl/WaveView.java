package com.owo.bom.bezier.impl;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by wangli on 10/28/16.
 */

public class WaveView extends View {
  public WaveView(Context context) {
    super(context);
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.FILL);
    postDelayed(new Runnable() {
      @Override
      public void run() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(WaveView.this, "offset", 0, getWidth());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            postInvalidate();
          }
        });
        animator.start();
      }
    }, 1000);
  }

  public void setOffset(float offset) {
    mXOffset = offset;
  }

  private float mXOffset = 0;
  private Path mPath = new Path();
  private Matrix mMatrix = new Matrix();
  private Paint mPaint = new Paint();

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    int h = getHeight() / 2;
    int w = getWidth();
    int amplitude = 100;
    {
      mPath.reset();
      mPath.moveTo(0, h);
      mPath.quadTo(w / 4, h + amplitude, w / 2, h);
      mPath.quadTo(w * 3 / 4, h - amplitude, w, h);
      mPath.quadTo(w * 5 / 4, h + amplitude, 3 * w / 2, h);
      mPath.quadTo(w * 7 / 4, h - amplitude, w * 2, h);
      mPath.lineTo(w * 2, getHeight());
      mPath.lineTo(0, getHeight());
      mPath.close();
      mMatrix.reset();
      mMatrix.postTranslate(-mXOffset, 0);
      mPath.transform(mMatrix);
      canvas.drawPath(mPath, mPaint);
    }
  }
}
