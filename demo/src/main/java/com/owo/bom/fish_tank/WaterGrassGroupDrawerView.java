package com.owo.bom.fish_tank;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wangli on 11/8/16.
 */

public class WaterGrassGroupDrawerView extends FrameLayout {
  private List<WaterGrassDrawer> mDrawers = new ArrayList<>();

  public WaterGrassGroupDrawerView(Context context) {
    this(context, 5);
  }

  public WaterGrassGroupDrawerView(Context context, int n) {
    super(context);
    for (int i = 0; i < n; i++) {
      WaterGrassDrawer drawer = new WaterGrassDrawer();
      if (i < n / 2) {
        drawer.setDirection(WaterGrassDrawer.DIRECTION_LEFT);
      }
      mDrawers.add(drawer);
    }
  }

  private Random mRandom = new Random(System.currentTimeMillis());

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (getWidth() == 0) {
      return;
    }

    int viewWidth = getWidth() / mDrawers.size();
    for (int i = 0; i < mDrawers.size(); i++) {
      canvas.save();
      WaterGrassDrawer drawer = mDrawers.get(i);
      int n = mRandom.nextInt(1000) % 10;
      n = (n <= 5) ? 5 : n;
      drawer.setN(n);
      drawer.setWidth(viewWidth);
      drawer.setHeight(getHeight());
      canvas.translate(viewWidth * i, 0);
      drawer.draw(canvas);
      canvas.restore();
    }
  }
}
