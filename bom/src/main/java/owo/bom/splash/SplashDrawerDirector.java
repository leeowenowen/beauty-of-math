package owo.bom.splash;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.PointF;

/**
 * Created by wangli on 9/19/16.
 */
public class SplashDrawerDirector extends DrawDirector {
  private PointF center;

  public SplashDrawerDirector(PointF center) {
    this.center = center;
    setupSections();
  }

  private void setupSections() {
    addSection(new Section() {
      private Drawer drawer = new PointToCircleDrawer(center);

      @Override
      public Drawer drawer() {
        return drawer;
      }

      @Override
      public ValueAnimator animator() {
        return ObjectAnimator.ofFloat(drawer, "radius", 0, 100).setDuration(2000);
      }
    });
    addSection(new Section() {
      private Drawer drawer = new CircleToCrossDrawer(center);

      @Override
      public Drawer drawer() {
        return drawer;
      }

      @Override
      public ValueAnimator animator() {
        return ObjectAnimator.ofPropertyValuesHolder(drawer,
                                                     PropertyValuesHolder.ofFloat("startRadius",
                                                                                  100,
                                                                                  1),
                                                     PropertyValuesHolder.ofFloat("endRadius",
                                                                                  50,
                                                                                  1),
                                                     PropertyValuesHolder.ofFloat(
                                                       "circleCenterDistance",
                                                       0,
                                                       500)).setDuration(10000);
      }
    });
    addSection(new Section() {
      private Drawer drawer = new CrossToAxisDrawer(center);

      @Override
      public Drawer drawer() {
        return drawer;
      }

      @Override
      public ValueAnimator animator() {
        return ObjectAnimator.ofPropertyValuesHolder(drawer,
                                                     PropertyValuesHolder.ofFloat("angle",
                                                                                  (float) Math.PI,
                                                                                  (float) Math.PI /
                                                                                  6))
                             .setDuration(5000);
      }
    });
  }
}
