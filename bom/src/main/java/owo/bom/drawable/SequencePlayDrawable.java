package owo.bom.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;


public class SequencePlayDrawable extends Drawable {
  private List<Drawable> drawables = new ArrayList<>();

  public void add(Drawable d) {
    drawables.add(d);
  }

  public void remove(Drawable d) {
    drawables.remove(d);
  }

  @Override
  public void draw(Canvas canvas) {
    for (Drawable d : drawables) {
      d.draw(canvas);
    }
  }

  @Override
  public void setAlpha(int alpha) {
    for (Drawable d : drawables) {
      d.setAlpha(alpha);
    }
  }

  @Override
  public void setColorFilter(ColorFilter colorFilter) {
    for (Drawable d : drawables) {
      d.setColorFilter(colorFilter);
    }
  }

  @Override
  public int getOpacity() {
    return 0;
  }
}
