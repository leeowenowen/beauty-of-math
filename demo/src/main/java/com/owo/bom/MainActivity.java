package com.owo.bom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import owo.bom.drawable.CircleToAxiDrawable;
import owo.bom.drawable.PointToCircleDrawable;
import owo.bom.util.UIUtil;
import owo.bom.util.WindowUtil;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  FrameLayout content;

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------
    content = new FrameLayout(this);
    setContentView(content);
    final View view = new View(this);
    content.addView(view, new FrameLayout.LayoutParams(200, 200, Gravity.CENTER));
    PointToCircleDrawable d = new PointToCircleDrawable(view);
    UIUtil.setBackgroundDrawable(view, d);

    ObjectAnimator animator = ObjectAnimator.ofFloat(d, "radius", 0, 100).setDuration(2000);
    animator.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        playCircleToAxis(view);

      }
    });
    animator.setDuration(5000);
    animator.start();


    //    AnimatorSet set = new AnimatorSet();
    //    set.play(animator);
    //    set.setInterpolator(new BounceInterpolator());
    //    set.setDuration(10000);
    //    set.start();

  }

  private void playCircleToAxis(View v) {
    int[] loc = new int[2];
    v.getLocationInWindow(loc);
    loc[1] = loc[1] - WindowUtil.getStatuBarHeight(MainActivity.this) -
             WindowUtil.getTitleBarHeight(MainActivity.this);
    ViewGroup vg = ((ViewGroup) v.getParent());
    vg.removeView(v);
    vg.addView(v,
               new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                            FrameLayout.LayoutParams.MATCH_PARENT));
    //    FrameLayout.LayoutParams fl = (FrameLayout.LayoutParams) v.getLayoutParams();
    //    fl.width = FrameLayout.LayoutParams.MATCH_PARENT;
    //    fl.height = FrameLayout.LayoutParams.MATCH_PARENT;
    CircleToAxiDrawable d =
      new CircleToAxiDrawable(v, new PointF(loc[0] + v.getWidth() / 2, loc[1] + v.getHeight() / 2));
    UIUtil.setBackgroundDrawable(v, d);
    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(d,
                                                                    PropertyValuesHolder.ofFloat(
                                                                      "startRadius",
                                                                      100,
                                                                      5),
                                                                    PropertyValuesHolder.ofFloat(
                                                                      "endRadius",
                                                                      50,
                                                                      5),
                                                                    PropertyValuesHolder.ofFloat(
                                                                      "circleCenterDistance",
                                                                      300,
                                                                      500));
    animator.setDuration(5000);
    animator.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        Log.d(TAG, "onAnimationStart");
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        Log.d(TAG, "onAnimationEnd");
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        Log.d(TAG, "onAnimationCancel");
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        Log.d(TAG, "onAnimationRepeat");
      }
    });
    animator.start();
  }

  private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
    int[] colors = new int[] {pressed, focused, normal, focused, unable, normal};
    int[][] states = new int[6][];
    states[0] = new int[] {android.R.attr.state_pressed, android.R.attr.state_enabled};
    states[1] = new int[] {android.R.attr.state_enabled, android.R.attr.state_focused};
    states[2] = new int[] {android.R.attr.state_enabled};
    states[3] = new int[] {android.R.attr.state_focused};
    states[4] = new int[] {android.R.attr.state_window_focused};
    states[5] = new int[] {};
    ColorStateList colorList = new ColorStateList(states, colors);
    return colorList;
  }
}
