package owo.bom.splash;

import android.animation.ValueAnimator;

/**
 * Created by wangli on 9/19/16.
 */
public abstract class AnimatedDrawerSection implements Section {
  private Drawer drawer;
  private ValueAnimator animator;

  public AnimatedDrawerSection(Drawer drawer) {
    this.drawer = drawer;
  }

  @Override
  public Drawer drawer() {
    return drawer;
  }

  @Override
  public ValueAnimator animator() {
    if (animator == null) {
      animator = createAnimator(drawer);
    }
    return animator;
  }

  protected abstract ValueAnimator createAnimator(Drawer drawer);
}
