package com.owo.bom.bezier.details;

import android.content.Context;
import android.view.View;

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
                return new SampleBezierViewLevel2(context);
            case BezierConstants.TYPE_ADVANCE_PAGETURN:
                return new SampleBezierViewLevel2(context);
            case BezierConstants.TYPE_ADVANCE_WATERFLOW:
                return new SampleBezierViewLevel2(context);
            case BezierConstants.TYPE_ADVANCE_DRAG:
                return new SampleBezierViewLevel2(context);
            case BezierConstants.TYPE_ADVANCE_FLOWER:
                return new SampleBezierViewLevel2(context);
            default:
                return null;
        }
    }
}
