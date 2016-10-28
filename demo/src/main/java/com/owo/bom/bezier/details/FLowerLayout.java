package com.owo.bom.bezier.details;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.owo.bom.R;
import com.owo.bom.base.SelfDrawView;

import owo.bom.drawer.FlowerDrawer;

/**
 * Created by wangli on 16-10-25.
 */
public class FLowerLayout extends LinearLayout {
  private static final String TAG = FLowerLayout.class.getSimpleName();
  private FlowerView mFlowerView;
  private SeekBarLayout mNSeekbar;
  private SeekBarLayout mRSeekbar;
  private SeekBarLayout mrSeekbar;

  public FLowerLayout(Context context) {
    super(context);
    setOrientation(LinearLayout.VERTICAL);
    mNSeekbar = new SeekBarLayout(context);
    mNSeekbar.seekbar().setMax(100);
    mNSeekbar.title().setText(R.string.petals_num);
    addView(mNSeekbar);

    mRSeekbar = new SeekBarLayout(context);
    mRSeekbar.seekbar().setMax(100);
    mRSeekbar.title().setText(R.string.outter_radius);
    addView(mRSeekbar);

    mrSeekbar = new SeekBarLayout(context);
    mrSeekbar.seekbar().setMax(100);
    mrSeekbar.title().setText(R.string.inner_radius);
    addView(mrSeekbar);

    mFlowerView = new FlowerView(context);
    addView(mFlowerView,
            new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    mNSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mRSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mrSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mNSeekbar.seekbar().setProgress(3);
    mRSeekbar.seekbar().setProgress(50);
    mrSeekbar.seekbar().setProgress(30);
  }

  private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      invalidate();
      mFlowerView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
  };

  public class FlowerView extends SelfDrawView {
    private FlowerDrawer mDrawer = new FlowerDrawer(null);

    public FlowerView(Context context) {
      super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
      if (getWidth() == 0 || getHeight() == 0) {
        return;
      }

      int n = mNSeekbar.seekbar().getProgress();
      float R = mRSeekbar.seekbar().getProgress() * getWidth() / (100 * 2);
      float r = mrSeekbar.seekbar().getProgress() * getWidth() / (100 * 2);

      mDrawer.center(new PointF(getWidth() / 2, getHeight() / 2)).setN(n).setRIn(r).setROut(R);
      mPaint.setColor(Color.RED);
      mPaint.setStyle(Paint.Style.FILL);
      mDrawer.draw(canvas);
    }
  }
}
