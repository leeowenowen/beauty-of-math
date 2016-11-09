package com.owo.bom.fish_tank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.owo.base.util.UIUtil;
import com.owo.bom.bezier.BezierActivity;
import com.owo.bom.bezier.impl.WaveView;
import com.owo.bom.lace.impl.CircleProgressView;

/**
 * Created by wangli on 11/8/16.
 */

public class FishTank extends FrameLayout {
  private WaveView mWaveView;
  private WaterGrassGroupDrawerView mLeftGrass;
  private WaterGrassGroupDrawerView mRightGrass;
  private SunView mSun;
  private CircleProgressView mCircle;
  private DandelionView mDandelion;
  private TextView mEntryView;

  public FishTank(final Context context) {
    super(context);
    mWaveView = new WaveView(context);
    mLeftGrass = new WaterGrassGroupDrawerView(context, 3);
    mRightGrass = new WaterGrassGroupDrawerView(context, 5);
    mSun = new SunView(context);
    mCircle = new CircleProgressView(context);
    mCircle.setRotationX(70);
    mDandelion = new DandelionView(context);
    mEntryView = new TextView(context);
    mEntryView.setGravity(Gravity.CENTER);
    UIUtil.setBackgroundDrawable(mEntryView, new ShapeDrawable(new RoundShape()));
    mEntryView.setText("Tutorial");
    mEntryView.setBackgroundColor(Color.GREEN);
    mEntryView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, BezierActivity.class);
        context.startActivity(intent);
      }
    });

    addView(mWaveView);
    addView(mLeftGrass);
    addView(mRightGrass);
    addView(mSun);
    addView(mCircle);
    addView(mDandelion);
    addView(mEntryView, new FrameLayout.LayoutParams(200, 200, Gravity.TOP | Gravity.LEFT));
    UIUtil.setMargin(mEntryView, 20, 20, 0, 0);
    setBackgroundColor(Color.BLUE);
  }

  private void setupLayout() {
    int grassWidth = getWidth() / 4;
    {
      FrameLayout.LayoutParams lp =
        new FrameLayout.LayoutParams(grassWidth, getHeight() / 2, Gravity.LEFT | Gravity.BOTTOM);
      mLeftGrass.setLayoutParams(lp);
    }
    {
      FrameLayout.LayoutParams lp =
        new FrameLayout.LayoutParams(grassWidth, getHeight() / 2, Gravity.RIGHT | Gravity.BOTTOM);
      lp.rightMargin = getWidth() / 10;
      mRightGrass.setLayoutParams(lp);
    }
    {
      FrameLayout.LayoutParams lp =
        new FrameLayout.LayoutParams(getWidth() / 2, getWidth() / 2, Gravity.RIGHT | Gravity.TOP);
      mSun.setLayoutParams(lp);
    }

    {
      FrameLayout.LayoutParams lp =
        new FrameLayout.LayoutParams(getWidth() / 3, getWidth() / 3, Gravity.BOTTOM | Gravity.LEFT);
      lp.leftMargin = getWidth() / 4;
      mCircle.setLayoutParams(lp);
    }
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (w == 0 || h == 0) {
      return;
    }
    setupLayout();
  }
}
