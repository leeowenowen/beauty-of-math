package com.owo.bom.bezier.impl;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.owo.base.util.UIUtil;

/**
 * Created by wangli on 10/28/16.
 */

public class SeekBarLayout extends LinearLayout {
  public TextView mTitle;
  public SeekBar mSeekbar;

  public SeekBarLayout(Context context) {
    super(context);
    mTitle = new TextView(context);
    mSeekbar = new SeekBar(context);
    addView(mTitle);
    addView(mSeekbar,
            new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, UIUtil.dp2px(context, 50)));
    UIUtil.setMargin(mTitle, 0, 0, UIUtil.dp2px(context, 5), 0);
  }

  public TextView title() {
    return mTitle;
  }

  public SeekBar seekbar() {
    return mSeekbar;
  }
}
