package com.owo.bom.bezier.impl;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import owo.bom.drawer.AnyBezierDrawer;
import owo.bom.drawer.BezierControlPointsMaker;
import owo.bom.splash.AnimatedDrawerSection;
import owo.bom.splash.DrawDirector;
import owo.bom.splash.Drawer;

/**
 * Created by wangli on 11/1/16.
 */

public class PrincipleView extends View {
  private DrawDirector mDrawDirector = new DrawDirector();

  public PrincipleView(Context context) {
    super(context);
    AnyBezierDrawer anyBezierDrawer = new AnyBezierDrawer();
    anyBezierDrawer.setControlPoints(BezierControlPointsMaker.make5(),
                                     BezierControlPointsMaker.makeColors(),
                                     200);
    mDrawDirector.addSection(new AnimatedDrawerSection(anyBezierDrawer) {
      @Override
      protected ValueAnimator createAnimator(Drawer drawer) {
        return ObjectAnimator.ofPropertyValuesHolder(drawer,
                                                     PropertyValuesHolder.ofInt("i", 0, 200))
                             .setDuration(20000);
      }
    });
    mDrawDirector.setAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        postInvalidate();
      }
    });
    mDrawDirector.start();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    mDrawDirector.draw(canvas);
  }
}
