package owo.bom.bezier;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;


public class BezierDrawer {
  private static final int MODE_TR = 1;
  private static final int MODE_TL = 2;
  private static final int MODE_BL = 3;
  private static final int MODE_BR = 4;

  private final PointF mStart;
  private final PointF mEnd;
  private final float mR;
  private final float mr;

  private float mAixOffset;
  private boolean mDrawStartCircle;
  private boolean mDrawEndCircle;
  private boolean mDrawPoints;

  private Path mTmpPath = new Path();

  public BezierDrawer(PointF start, PointF end, float R, float r,) {
    mStart = start;
    mEnd = end;
    mR = R;
    mr = r;
  }

  public BezierDrawer withStartCircle() {
    mDrawStartCircle = true;
    return this;
  }

  public BezierDrawer withEndCircle() {
    mDrawEndCircle = true;
    return this;
  }

  public BezierDrawer withPoints() {
    mDrawPoints = true;
    return this;
  }

  public BezierDrawer withAxiOffset(float axiOffset) {
    mAixOffset = axiOffset;
    return this;
  }

  private int getMode(PointF start, PointF end) {
    if (end.x >= start.x) {
      if (end.y >= start.y) {
        return MODE_TR;
      } else {
        return MODE_BR;
      }
    } else {
      if (end.y >= start.y) {
        return MODE_TL;
      } else {
        return MODE_BL;
      }
    }
  }

  public void draw(Canvas canvas, Paint paint) {
    int mode = getMode(mStart, mEnd);
    double xa = 0;
    double ya = 0;
    double xb = 0;
    double yb = 0;
    double xc = 0;
    double yc = 0;
    double xd = 0;
    double yd = 0;

    double xc1;
    double yc1;

    double lRr = Math.sqrt(
      (mStart.x - mEnd.x) * (mStart.x - mEnd.x) + (mStart.y - mEnd.y) * (mStart.y - mEnd.y));

    double factor = mR / (mR + mr);
    double lR = lRr * factor;
    double lr = lRr - lR;

    //mapping to TR
    double startX = this.mStart.x;
    double startY = this.mStart.y;
    //translate
    this.mStart.x = 0;
    this.mStart.y = 0;
    this.mEnd.x -= this.mStart.x;
    this.mEnd.y -= this.mStart.y;
    //map
    switch (mode) {
      case MODE_TR:
        break;
      case MODE_TL:
        this.mEnd.x = -this.mEnd.x;
        break;
      case MODE_BL:
        this.mEnd.x = -this.mEnd.x;
        this.mEnd.y = -this.mEnd.y;
        break;
      case MODE_BR:
        this.mEnd.y = -this.mEnd.y;
        break;
    }
    xc1 = mStart.x + Math.abs(mEnd.x - mStart.x) * factor;
    yc1 = mStart.y + Math.abs(mEnd.y - mStart.y) * factor;
    //process as in
    {
      double alpha = Math.acos(mR / lR);
      double beta = Math.atan2(yc1 - mStart.y, xc1 - mStart.x);
      xa = mStart.x + mR * Math.cos(alpha + beta);
      ya = mStart.y + mR * Math.sin(alpha + beta);
      xc = mStart.x + mR * Math.cos(alpha - beta);
      yc = mStart.y - mR * Math.sin(alpha - beta);


      xb = xc + (xc1 - xc) * lRr / lR;
      yb = yc + (yc1 - yc) * lRr / lR;

      xd = xa + (xc1 - xa) * lRr / lR;
      yd = ya + (yc1 - ya) * lRr / lR;
    }
    //de-map
    switch (mode) {
      case MODE_TR:
        break;
      case MODE_TL:
        xa = -xa;
        xc = -xc;
        xb = -xb;
        xd = -xd;
        xc1 = -xc1;
        break;
      case MODE_BL:
        xa = -xa;
        ya = -ya;
        xb = -xb;
        yb = -yb;
        xc = -xc;
        yc = -yc;
        xd = -xd;
        yd = -yd;
        xc1 = -xc1;
        yc1 = -yc1;
        break;
      case MODE_BR:
        ya = -ya;
        yb = -yb;
        yc = -yc;
        yd = -yd;
        yc1 = -yc1;
        break;
    }
    // de-translate
    {
      xa += startX;
      xb += startX;
      xc += startX;
      xd += startX;
      ya += startY;
      yb += startY;
      yc += startY;
      yd += startY;
      xc1 += startX;
      yc1 += startY;
    }
    if (mAixOffset > 0) {
      xa += mAixOffset;
      ya += mAixOffset;
      xb += mAixOffset;
      yb += mAixOffset;
      xc += mAixOffset;
      yc += mAixOffset;
      xd += mAixOffset;
      yd += mAixOffset;
      mStart.x += mAixOffset;
      mStart.y += mAixOffset;
      mEnd.x += mAixOffset;
      mEnd.y += mAixOffset;
      xc1 += mAixOffset;
      yc1 += mAixOffset;
    }
    if (mDrawStartCircle) {
      canvas.drawCircle(mStart.x, mStart.y, mR, paint);
    }
    if (mDrawEndCircle) {
      canvas.drawCircle(mEnd.x, mEnd.y, mr, paint);
    }

    if (mDrawPoints) {
      canvas.drawText("a", (float) xa, (float) ya, paint);
      canvas.drawText("b", (float) xb, (float) yb, paint);
      canvas.drawText("c", (float) xc, (float) yc, paint);
      canvas.drawText("d", (float) xd, (float) yd, paint);
      canvas.drawText("C", (float) xc1, (float) yc1, paint);
    }

    mTmpPath.reset();
    mTmpPath.moveTo((float) xa, (float) ya);
    mTmpPath.quadTo((float) xc1, (float) yc1, (float) xb, (float) yb);
    mTmpPath.moveTo((float) xc, (float) yc);
    mTmpPath.quadTo((float) xc1, (float) yc1, (float) xd, (float) yd);
    canvas.drawPath(mTmpPath, paint);

  }

}
