package com.owo.bom;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import owo.bom.WaterDropView;

public class MainActivity extends AppCompatActivity {
  FrameLayout content;

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //-------------------------------------------------
    //    content = new FrameLayout(this);
    //    setContentView(content);
    //    View view = new View(this);
    //    PointToCircleDrawable d = new PointToCircleDrawable(view);
    //    UIUtil.setBackgroundDrawable(view, d);
    //    content.addView(view, new FrameLayout.LayoutParams(500, 500, Gravity.CENTER));
    //    ObjectAnimator animator1 = ObjectAnimator.ofFloat(d, "radius", 0.0f, 200f);
    //    AnimatorSet set = new AnimatorSet();
    //    set.play(animator1);
    //    set.setInterpolator(new BounceInterpolator());
    //    set.setDuration(5000);
    //    set.start();
    //-------------------------------------------------
    //   Debug.waitForDebugger();
    //        Bezier view = new Bezier(this);
    //        Point[] pts = new Point[]{
    ////                new Point(100, 150), new Point(80, 100), new Point(50, 50), new Point(100, 0),
    ////                new Point(150, 50), new Point(120, 100), new Point(100, 150),
    //                new Point(0, 0), new Point(100, 150), new Point(300, 150), new Point(450, 0),
    //        };
    //  view.setControlPoints(pts);
    //  SampleBezierView view = new SampleBezierView(this);
    WaterDropView view = new WaterDropView(this);
    setContentView(view);

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
