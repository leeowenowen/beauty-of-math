package com.owo.bom.bezier.details;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.owo.bom.bezier.BezierConstants;

/**
 * Created by wangli on 16-10-24.
 */
public class BezierSampleViewFactory {
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
      default:
        return null;
    }
  }
}
