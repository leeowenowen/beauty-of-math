package com.owo.bom.lace.impl;


import android.content.Context;
import android.view.View;

import com.owo.bom.lace.LaceConstants;

import owo.bom.ArrowView;
import owo.bom.CloverView;
import owo.bom.GrassView;
import owo.bom.InvoluteView;
import owo.bom.SpiralView;
import owo.bom.StarView;
import owo.bom.VerticalView;
import owo.bom.Y_ax_b_View;
import owo.bom.sincos_View;

/**
 * Created by wangli on 16-10-24.
 */
public class LaceSampleViewFactory {
  public View create(Context context, String type) {
    switch (type) {
      case LaceConstants.TYPE_CURVE_LINEAR_FUNCTION:
        return new Y_ax_b_View(context);
      case LaceConstants.TYPE_CURVE_SINCOS:
        return new sincos_View(context);
      case LaceConstants.TYPE_CURVE_ARROW:
        return new ArrowView(context);
      case LaceConstants.TYPE_CURVE_CLOVER:
        return new CloverView(context);
      case LaceConstants.TYPE_CURVE_INVOLUTE:
        return new InvoluteView(context);
      case LaceConstants.TYPE_CURVE_GRASS:
        return new GrassView(context);
      case LaceConstants.TYPE_CURVE_SPIRAL:
        return new SpiralView(context);
      case LaceConstants.TYPE_CURVE_STAR:
        return new StarView(context);
      case LaceConstants.TYPE_CURVE_VERTICAL:
        return new VerticalView(context);
      default:
        return null;
    }
  }
}
