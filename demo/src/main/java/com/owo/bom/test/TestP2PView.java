package com.owo.bom.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.widget.TextView;

/**
 * Created by wangli on 11/9/16.
 */

public class TestP2PView extends TextView {
  public TestP2PView(Context context) {
    super(context);
    String text = "";
    for (int i = 0; i < 100; i++) {
      text += "AAAAAAAAAAAAAAAAAAAAAAA";
    }
    setText(text);
  }

  private Canvas mCanvas;
  private Bitmap mMemBmp;
  private Matrix mMatrix = new Matrix();
  private Path mPath = new Path();

  Point[] pts;

  @Override
  protected void onDraw(Canvas canvas) {

    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }

    if (mCanvas == null) {
      mMemBmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
      mCanvas = new Canvas(mMemBmp);
      pts = new Point[2];
      pts[0] = new Point(0, getHeight());
      pts[1] = new Point(getWidth(), getHeight());
    }

    mPath.moveTo(0, 0);
    mPath.quadTo(0, getHeight(), getWidth(), getHeight());
    super.onDraw(mCanvas);


  }

}
