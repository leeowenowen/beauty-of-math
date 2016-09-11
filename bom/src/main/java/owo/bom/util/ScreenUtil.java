package owo.bom.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by wangli on 16-9-11.
 */
public class ScreenUtil {
    private static DisplayMetrics dm = new DisplayMetrics();

    public static int screenWidth(Activity activity) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int screenHeight(Activity activity) {
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
