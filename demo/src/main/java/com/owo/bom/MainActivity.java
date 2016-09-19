package com.owo.bom;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import owo.bom.drawable.BaseDrawable;
import owo.bom.splash.SplashDrawerDirector;
import owo.bom.util.UIUtil;
import owo.bom.util.WindowUtil;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    //-------------------------------------------------
    final View view = new View(this);
    setContentView(view);
    PointF center = new PointF();
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

}
