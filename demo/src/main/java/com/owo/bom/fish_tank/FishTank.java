package com.owo.bom.fish_tank;

import android.content.Context;
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
  private PerlView mPerl;
  private CircleProgressView mCircle;

  public FishTank(Context context) {
    super(context);
    mWaveView = new WaveView(context);
    mLeftGrass = new WaterGrassGroupDrawerView(context, 3);
    mRightGrass = new WaterGrassGroupDrawerView(context, 5);
    mPerl = new PerlView(context);
    mCircle = new CircleProgressView(context);

    addView(mWaveView);
    addView(mLeftGrass);
    addView(mRightGrass);
    addView(mPerl);
    addView(mCircle);
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
      FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(getWidth() / 2,
                                                                 getWidth() / 2,
                                                                 Gravity.RIGHT | Gravity.BOTTOM);
//      lp.rightMargin = -getWidth() / 4;
//      lp.bottomMargin = -getWidth() / 4;
      mPerl.setLayoutParams(lp);
    }

    {
      FrameLayout.LayoutParams lp =
        new FrameLayout.LayoutParams(getWidth() / 2, getWidth() / 2, Gravity.BOTTOM);
      lp.bottomMargin = getWidth() / 4;
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
