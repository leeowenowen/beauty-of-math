package com.owo.bom.base;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by wangli on 16-10-25.
 */
public class SelfDrawView extends View {
    protected static Paint mPaint = new Paint();
    protected static Path mPath = new Path();

    public SelfDrawView(Context context) {
        super(context);
        mPaint.setAntiAlias(false);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
