package owo.bom.util;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Window;

/**
 * Created by wangli on 9/17/16.
 */
public class WindowUtil {
  public static DisplayMetrics getScreenSize(Activity activity) {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm;
  }

  public static int getStatuBarHeight(Activity activity) {
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    return frame.top;
  }

  public static int getTitleBarHeight(Activity activity) {
    int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    return contentTop;
  }
}
