package com.owo.bom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;

import owo.bom.drawable.CircleToAxiDrawable;
import owo.bom.util.UIUtil;

public class MainActivity extends AppCompatActivity {
  FrameLayout content;

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------
    content = new FrameLayout(this);
    setContentView(content);
    View view = new View(this);
    // PointToCircleDrawable d = new PointToCircleDrawable(view);
    CircleToAxiDrawable d = new CircleToAxiDrawable(view, new PointF(400, 400));
    UIUtil.setBackgroundDrawable(view, d);
    content.addView(view,
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                 FrameLayout.LayoutParams.MATCH_PARENT,
                                                 Gravity.CENTER));
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

    AnimatorSet set = new AnimatorSet();
    set.play(animator);
    set.setInterpolator(new BounceInterpolator());
    set.setDuration(50000);
    set.start();

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
