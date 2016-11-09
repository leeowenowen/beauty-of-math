package com.owo.bom.fish_tank;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import owo.bom.drawer.FlowerDrawer;
import owo.bom.splash.Drawer;

/**
 * Created by wangli on 11/9/16.
 */

public class DandelionView extends View {
  private static final String TAG = DandelionView.class.getSimpleName();
  private Random mRandom = new Random(System.currentTimeMillis());
  private Set<FlowerDrawer> mDrawers = new HashSet<>();

  public DandelionView(Context context) {
    super(context);
    postDelayed(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 3; ++i) {
          randomGenerate();
        }
        postDelayed(this, 2000);
      }
    }, 2000);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    for (Drawer d : mDrawers) {
      d.draw(canvas);
    }
  }

  private class AnimatedPointF extends PointF {
    public AnimatedPointF(float x, float y) {
      super(x, y);
    }

    public void setCX(float x) {
      this.x = x;
    }

    public void setCY(float y) {
      this.y = y;
    }
  }

  private int dir() {
    return ((mRandom.nextInt() % 2) == 0) ? 1 : -1;
  }

  private void randomGenerate() {
    final AnimatedPointF point = randomPosition();
    int rIn = mRandom.nextInt(100);
    int rOut = mRandom.nextInt(100);
    Log.d(TAG, "randomPosition X:" + point.x + " Y:" + point.y + " rIn:" + rIn + " rOut:" + rOut);
    final FlowerDrawer drawer = new FlowerDrawer(point).setN(mRandom.nextInt(50));
    drawer.mPaint.setColor(Color.WHITE);
    Keyframe kx1 = Keyframe.ofFloat(0, point.x);
    Keyframe kx2 = Keyframe.ofFloat(0.75f, point.x + 50 * dir());
    Keyframe kx3 = Keyframe.ofFloat(1f, point.x + 200 * dir());
    Keyframe ky1 = Keyframe.ofFloat(0f, getHeight());
    Keyframe ky2 = Keyframe.ofFloat(0.25f, getHeight() - getHeight() / 5);
    Keyframe ky3 = Keyframe.ofFloat(0.75f, getHeight() - getHeight() / 2);
    Keyframe ky4 = Keyframe.ofFloat(1f, 0);
    PropertyValuesHolder kxHolder = PropertyValuesHolder.ofKeyframe("CX", kx1, kx2, kx3);
    PropertyValuesHolder kyHolder = PropertyValuesHolder.ofKeyframe("CY", ky1, ky2, ky3, ky4);
    ObjectAnimator oap = ObjectAnimator.ofPropertyValuesHolder(point, kxHolder, kyHolder);
    PropertyValuesHolder rinHolder = PropertyValuesHolder.ofFloat("RIn", 0, rIn);
    PropertyValuesHolder routHolder = PropertyValuesHolder.ofFloat("ROut", 0, rOut);
    ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(drawer, rinHolder, routHolder);
    AnimatorSet os = new AnimatorSet();
    os.playTogether(oap, oa);
    os.setDuration(10000);
    os.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        mDrawers.add(drawer);
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        mDrawers.remove(drawer);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        mDrawers.remove(drawer);
      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    });
    oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        postInvalidate();
      }
    });
    os.start();
  }

  private AnimatedPointF randomPosition() {
    int x = mRandom.nextInt(getWidth());
    int y = getHeight();

    return new AnimatedPointF(x, y);
  }
}
