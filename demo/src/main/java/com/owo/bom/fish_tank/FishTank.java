package com.owo.bom.fish_tank;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;

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

  public FishTank(Context context) {
    super(context);
    mWaveView = new WaveView(context);
    mLeftGrass = new WaterGrassGroupDrawerView(context, 3);
    mRightGrass = new WaterGrassGroupDrawerView(context, 5);
    mSun = new SunView(context);
    mCircle = new CircleProgressView(context);
    mCircle.setRotationX(70);
    mDandelion = new DandelionView(context);

    addView(mWaveView);
    addView(mLeftGrass);
    addView(mRightGrass);
    addView(mSun);
    addView(mCircle);
    addView(mDandelion);
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
