package com.owo.bom.lace.impl;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.owo.bom.bezier.BezierConstants;
import com.owo.bom.bezier.impl.BezierView;
import com.owo.bom.bezier.impl.DragViewCircumscribe;
import com.owo.bom.bezier.impl.DragViewInscribe;
import com.owo.bom.bezier.impl.FLowerLayout;
import com.owo.bom.bezier.impl.PageTurnView;
import com.owo.bom.bezier.impl.PrincipleView;
import com.owo.bom.bezier.impl.SampleBezierViewLevel2;
import com.owo.bom.bezier.impl.SampleBezierViewLevel3;
import com.owo.bom.bezier.impl.WaveView;

/**
 * Created by wangli on 16-10-24.
 */
public class LaceSampleViewFactory {
  public View create(Context context, String type) {
    switch (type) {
      case BezierConstants.TYPE_BASIC_LEVEL1:
        return new SampleBezierViewLevel2(context);
      case BezierConstants.TYPE_BASIC_LEVEL2:
        return new SampleBezierViewLevel2(context);
      case BezierConstants.TYPE_BASIC_LEVEL3:
        return new SampleBezierViewLevel3(context);
      case BezierConstants.TYPE_BASIC_LEVELN:
        return new BezierView(context);
      case BezierConstants.TYPE_ADVANCE_PAGETURN:
        return new PageTurnView(context);
      case BezierConstants.TYPE_ADVANCE_WATERFLOW:
        return new WaveView(context);
      case BezierConstants.TYPE_ADVANCE_DRAG:
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        DragViewCircumscribe circumscribe = new DragViewCircumscribe(context);
        DragViewInscribe inscribe = new DragViewInscribe(context);
        layout.addView(circumscribe,
                       new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        layout.addView(inscribe,
                       new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        return layout;
      case BezierConstants.TYPE_ADVANCE_FLOWER:
        return new FLowerLayout(context);
      case BezierConstants.TYPE_PRINCIPLE:
        return new PrincipleView(context);
      default:
        return null;
    }
  }
}
