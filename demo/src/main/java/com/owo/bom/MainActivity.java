package com.owo.bom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import owo.bom.drawable.CircleToCrossDrawable;
import owo.bom.drawable.CrossToAxisDrawable;
import owo.bom.drawable.PointToCircleDrawable;
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
        content.addView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        //view.getLocationInWindow(center);
        DisplayMetrics dm = WindowUtil.getScreenSize(this);
        center.x = dm.widthPixels / 2;
        center.y = dm.heightPixels / 2;

        PointToCircleDrawable d = new PointToCircleDrawable(center);
        UIUtil.setBackgroundDrawable(view, d);

        ObjectAnimator animator = ObjectAnimator.ofFloat(d, "radius", 0, 100).setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                playCircleToCross(view);

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.invalidate();
            }
        });
        animator.start();
    }

    private void playCircleToCross(final View v) {
        ViewGroup vg = ((ViewGroup) v.getParent());
        vg.removeView(v);
        vg.addView(v,
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));
        CircleToCrossDrawable d =
                new CircleToCrossDrawable(center);
        UIUtil.setBackgroundDrawable(v, d);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(d,
                PropertyValuesHolder.ofFloat(
                        "startRadius",
                        100,
                        1),
                PropertyValuesHolder.ofFloat(
                        "endRadius",
                        50,
                        1),
                PropertyValuesHolder.ofFloat(
                        "circleCenterDistance",
                        0,
                        500));
        animator.setDuration(10000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                playCrossToAxis(v);

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.invalidate();
            }
        });
        animator.start();
    }

    private void playCrossToAxis(final View v) {
        ViewGroup vg = ((ViewGroup) v.getParent());
        vg.removeView(v);
        vg.addView(v,
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT));
        CrossToAxisDrawable d =
                new CrossToAxisDrawable(center);
        d.setArrowOffset(450);
        d.setArrowLength(50);
        UIUtil.setBackgroundDrawable(v, d);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(d,
                PropertyValuesHolder.ofFloat(
                        "angle",
                        (float) Math.PI,
                        (float) Math.PI / 6));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.invalidate();
            }
        });
        animator.setDuration(5000);
        animator.start();
    }

    private ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[]{pressed, focused, normal, focused, unable, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};
        states[3] = new int[]{android.R.attr.state_focused};
        states[4] = new int[]{android.R.attr.state_window_focused};
        states[5] = new int[]{};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
