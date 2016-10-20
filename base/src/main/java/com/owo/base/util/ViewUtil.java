package com.owo.base.util;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangli on 16-10-20.
 */
public class ViewUtil {
    public static void clearParent(View v) {
        if (v == null || v.getParent() == null) {
            return;
        }
        ViewGroup vg = (ViewGroup) v.getParent();
        vg.removeView(v);
    }
}
