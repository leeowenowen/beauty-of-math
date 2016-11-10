package com.owo.bom.fish_tank;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RectShape;

/**
 * Created by wangli on 11/9/16.
 */

public class RoundShape extends RectShape {
  private int color;

  public RoundShape(int color) {
    this.color = color;
  }

  @Override
  public void draw(Canvas canvas, Paint paint) {
    int oldColor = paint.getColor();
    paint.setColor(color);
    RectF rectF = rect();
    float x = rectF.left + rectF.width() / 2;
    float y = rectF.top + rectF.height() / 2;
    float max = (rectF.width() > rectF.height()) ? rectF.width() : rectF.height();
    float r = max / 2;
    canvas.drawCircle(x, y, r, paint);
    paint.setColor(oldColor);
  }
}
