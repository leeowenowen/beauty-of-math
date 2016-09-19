package owo.bom.splash;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by wangli on 9/17/16.
 */
public abstract class BaseDrawer implements Drawer {
  protected Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public BaseDrawer() {
    mPaint.setColor(Color.RED);
  }
}
