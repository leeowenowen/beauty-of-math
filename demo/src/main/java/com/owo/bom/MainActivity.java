package com.owo.bom;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import owo.bom.drawable.BaseDrawable;
import owo.bom.splash.SplashDrawerDirector;
import owo.bom.util.UIUtil;
import owo.bom.util.WindowUtil;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  FrameLayout content;
  private PointF center = new PointF();

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------
    content = new FrameLayout(this);
    setContentView(content);
    final View view = new View(this);
    content.addView(view,
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                 FrameLayout.LayoutParams.MATCH_PARENT));
    //view.getLocationInWindow(center);
    DisplayMetrics dm = WindowUtil.getScreenSize(this);
    center.x = dm.widthPixels / 2;
    center.y = dm.heightPixels / 2;
    final SplashDrawerDirector director = new SplashDrawerDirector(center);
    UIUtil.setBackgroundDrawable(view, new BaseDrawable() {
      @Override
      public void draw(Canvas canvas) {
        director.draw(canvas);
      }
    });
    director.setAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        view.invalidate();
      }
    });
    director.start();

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
