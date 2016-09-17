package owo.bom.drawable;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by wangli on 9/17/16.
 */
public abstract class BaseDrawable extends Drawable {
  protected Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  @Override
  public void setAlpha(int alpha) {
    mPaint.setAlpha(alpha);
  }

  @Override
  public void setColorFilter(ColorFilter colorFilter) {
    mPaint.setColorFilter(colorFilter);
  }

  @Override
  public int getOpacity() {
    int alpha = mPaint.getAlpha();
    if (alpha == 0) {
      return PixelFormat.TRANSPARENT;
    }
    if (alpha == 255) {
      return PixelFormat.OPAQUE;
    }
    return PixelFormat.TRANSLUCENT;
  }
}
