package com.owo.bom.fish_tank;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.owo.bom.base.SelfDrawView;

import owo.bom.drawer.FlowerDrawer;

/**
 * Created by wangli on 11/8/16.
 */

public class PerlView extends SelfDrawView {
  private FlowerDrawer mDrawer = new FlowerDrawer(null);

  public PerlView(Context context) {
    super(context);
    setBackgroundColor(Color.TRANSPARENT);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (mDrawer == null || w == 0 || h == 0) {
      return;
    }
    mDrawer.center(new PointF(getWidth() / 2, getHeight() / 2)).setN(10);
    PropertyValuesHolder rHolder =
      PropertyValuesHolder.ofFloat("rIn", getWidth() / 4, getWidth() / 2);
    PropertyValuesHolder RHolder =
      PropertyValuesHolder.ofFloat("rOut", getWidth() / 4 - 100, getWidth() / 2);
    ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(mDrawer, rHolder, RHolder);
    oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        postInvalidate();
      }
    });
    oa.setDuration(2000);
    oa.setRepeatCount(ValueAnimator.INFINITE);
    oa.start();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    mDrawer.mPaint.setColor(Color.YELLOW);
    mDrawer.mPaint.setStyle(Paint.Style.FILL);
    mDrawer.draw(canvas);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, mDrawer.mPaint);
  }

}
