package com.owo.bom.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.owo.bom.base.SelfDrawView;
import com.owo.bom.bezier.impl.SeekBarLayout;

import owo.bom.drawer.FlowerDrawer;

/**
 * Created by wangli on 16-10-25.
 */
public class TestLayout extends LinearLayout {
  private static final String TAG = TestLayout.class.getSimpleName();
  private TestCamera mTestCamera;
  private SeekBarLayout mXSeekbar;
  private SeekBarLayout mYSeekbar;
  private SeekBarLayout mZSeekbar;
  private SeekBarLayout mTXSeekbar;
  private SeekBarLayout mTYSeekbar;
  private SeekBarLayout mTZSeekbar;

  public TestLayout(Context context) {
    super(context);
    setOrientation(LinearLayout.VERTICAL);
    mXSeekbar = new SeekBarLayout(context);
    mXSeekbar.seekbar().setMax(100);
    mXSeekbar.title().setText("X:");
    addView(mXSeekbar);

    mYSeekbar = new SeekBarLayout(context);
    mYSeekbar.seekbar().setMax(100);
    mYSeekbar.title().setText("Y:");
    addView(mYSeekbar);

    mZSeekbar = new SeekBarLayout(context);
    mZSeekbar.seekbar().setMax(100);
    mZSeekbar.title().setText("Z:");
    addView(mZSeekbar);

    mTXSeekbar = new SeekBarLayout(context);
    mTXSeekbar.seekbar().setMax(100);
    mTXSeekbar.title().setText("TX:");
    addView(mTXSeekbar);

    mTYSeekbar = new SeekBarLayout(context);
    mTYSeekbar.seekbar().setMax(100);
    mTYSeekbar.title().setText("TY:");
    addView(mTYSeekbar);

    mTZSeekbar = new SeekBarLayout(context);
    mTZSeekbar.seekbar().setMax(100);
    mTZSeekbar.title().setText("TZ:");
    addView(mTZSeekbar);

    mTestCamera = new TestCamera(context);
    addView(mTestCamera, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    mXSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mYSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mZSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mXSeekbar.seekbar().setProgress(0);
    mYSeekbar.seekbar().setProgress(0);
    mZSeekbar.seekbar().setProgress(0);
    mTXSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mTYSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mTZSeekbar.seekbar().setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    mTXSeekbar.seekbar().setProgress(0);
    mTYSeekbar.seekbar().setProgress(0);
    mTZSeekbar.seekbar().setProgress(0);
  }

  private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      int rotate = progress * 360 / 100;
      if (seekBar.equals(mXSeekbar.mSeekbar)) {
        mTestCamera.setRX(rotate);
      } else if (seekBar.equals(mYSeekbar.mSeekbar)) {
        mTestCamera.setRY(rotate);
      } else if (seekBar.equals(mZSeekbar.mSeekbar)) {
        mTestCamera.setRZ(rotate);
      } else if (seekBar.equals(mTXSeekbar.mSeekbar)) {
        mTestCamera.mTX = rotate;
      } else if (seekBar.equals(mTYSeekbar.mSeekbar)) {
        mTestCamera.mTY = rotate;
      } else if (seekBar.equals(mTZSeekbar.mSeekbar)) {
        mTestCamera.mTZ = rotate;
      }
      invalidate();
      mTestCamera.invalidate();
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

      int n = mXSeekbar.seekbar().getProgress();
      float R = mYSeekbar.seekbar().getProgress() * getWidth() / (100 * 2);
      float r = mZSeekbar.seekbar().getProgress() * getWidth() / (100 * 2);

      mDrawer.center(new PointF(getWidth() / 2, getHeight() / 2)).setN(n).setRIn(r).setROut(R);
      mPaint.setColor(Color.RED);
      mPaint.setStyle(Paint.Style.FILL);
      mDrawer.draw(canvas);
    }
  }
}
