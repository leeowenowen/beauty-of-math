package com.owo.base.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangli on 9/15/16.
 */
public class UIUtil {
    public static void setBackgroundDrawable(View v, Drawable d) {
        if (Build.VERSION.SDK_INT >= 16) {
            v.setBackground(d);
        } else {
            v.setBackgroundDrawable(d);
        }
    }

    public static void setMargin(View v, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        lParams.leftMargin = left;
        lParams.topMargin = top;
        lParams.rightMargin = right;
        lParams.bottomMargin = bottom;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
