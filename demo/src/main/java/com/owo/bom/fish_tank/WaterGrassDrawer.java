package com.owo.bom.fish_tank;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import owo.bom.splash.Drawer;

/**
 * Created by wangli on 11/8/16.
 */

public class WaterGrassDrawer implements Drawer {
  private Path mPath = new Path();
  private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public WaterGrassDrawer() {
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.GREEN);
  }

  @Override
  public void draw(Canvas canvas) {
    if (mDirection == DIRECTION_LEFT) {
      Camera camera = new Camera();
      camera.translate(mWidth, 0, 0);
      camera.rotate(0, 180, 0);
      camera.applyToCanvas(canvas);
    }
    doDraw(canvas);

  }

  private int mWidth, mHeight;
  private int mControlDeltaX;
  private int mControlDeltaY;
  private int mN = 8;
  private int m2WOffset;
  private int m2HOffset;
  private Point[] mPoints;
  private Point[] mPoints2;
  public static final int DIRECTION_LEFT = 1;
  public static final int DIRECTION_RIGHT = 2;
  private int mDirection = DIRECTION_RIGHT;

  public void setDirection(int direction) {
    mDirection = direction;
  }

  public void setN(int n) {
    mPoints = null;
    mN = n;
  }

  public void setW(int w) {
    mPoints = null;
    m2WOffset = w;
  }

  public void setH(int h) {
    mPoints = null;
    m2HOffset = h;
  }

  public void setControlX(int cx) {
    mPoints = null;
    mControlDeltaX = cx;
  }

  public void setWidth(int width) {
    mWidth = width;
  }

  public void setHeight(int height) {
    mHeight = height;
  }

  private void computePoints() {
    mControlDeltaX = mWidth / (mN);
    m2WOffset = mWidth / mN;
    m2HOffset = mHeight / mN;
    mPoints = new Point[mN];
    mPoints2 = new Point[mN];
    mPoints[0] = new Point(0, mHeight);
    mPoints2[0] = mPoints[0];
    int deltaX = mWidth / (mN);
    int deltaY = mHeight / (mN);
    for (int i = 0; i < mN; ++i) {
      int ratio = 0;
      if (i != 0 && i != mN - 1) {
        ratio = (i % 2 == 0) ? 1 : -1;
      }
      mPoints[i] = new Point(i * deltaX + mControlDeltaX * ratio,
                             mHeight - (i * deltaY + mControlDeltaY * ratio));
      if (i != mN - 1) {
        mPoints2[i] = new Point(mPoints[i].x + m2WOffset, mPoints[i].y + m2HOffset);
      } else {
        mPoints2[i] = new Point(mPoints[i].x, mPoints[i].y);
      }
    }
  }

  private void doDraw(Canvas canvas) {
    if (mPoints == null) {
      computePoints();
      reverse(mPoints2);
    }
    mPaint.setColor(Color.parseColor("#EE1289"));
    for (int i = 0; i < mPoints.length; i++) {
      canvas.drawCircle(mPoints[i].x, mPoints[i].y, 5, mPaint);
    }

    for (int i = 0; i < mPoints2.length; i++) {
      canvas.drawCircle(mPoints2[i].x, mPoints2[i].y, 5, mPaint);
    }
    mPaint.setColor(Color.GREEN);
    mPath.reset();
    mPath.moveTo((float) mPoints[0].x, (float) mPoints[0].y);
    addBezierToPath(mPoints, mPath);
    addBezierToPath(mPoints2, mPath);
    canvas.drawPath(mPath, mPaint);
  }


  private <T> void reverse(T[] array) {
    for (int i = 0; i < array.length / 2; i++) {
      T tmp = array[i];
      array[i] = array[array.length - 1 - i];
      array[array.length - 1 - i] = tmp;
    }
  }


  private void addBezierToPath(Point[] pts, Path path) {
    for (int i = 1; i < pts.length; i++) {
      float endx, endy;
      if (i == pts.length - 1) {
        endx = pts[i].x;
        endy = pts[i].y;
      } else {
        endx = (pts[i + 1].x + pts[i].x) / 2;
        endy = (pts[i + 1].y + pts[i].y) / 2;
      }
      path.quadTo(pts[i].x, pts[i].y, endx, endy);
    }
  }
}
