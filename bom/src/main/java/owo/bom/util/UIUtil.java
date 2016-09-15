package owo.bom.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

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
}
