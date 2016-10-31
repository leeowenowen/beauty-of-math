package owo.bom.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;


public class CircleBezierDrawerCircumscribe {
  private static final int MODE_TR = 1;
  private static final int MODE_TL = 2;
  private static final int MODE_BL = 3;
  private static final int MODE_BR = 4;

  private final PointF mStartInit = new PointF();
  private final PointF mEndInit = new PointF();
  private final PointF mStart = new PointF();
  private final PointF mEnd = new PointF();
  private float mR;
  private float mr;

  private float mAixOffset;
  private boolean mDrawStartCircle;
  private boolean mDrawEndCircle;
  private boolean mDrawPoints;

  private Path mTmpPath = new Path();

  public CircleBezierDrawerCircumscribe() {
  }

  public CircleBezierDrawerCircumscribe start(float x, float y) {
    mStartInit.x = x;
    mStartInit.y = y;
    return this;
  }

  public CircleBezierDrawerCircumscribe R(float R) {
    mR = R;
    return this;
  }

  public CircleBezierDrawerCircumscribe r(float r) {
    mr = r;
    return this;
  }

  public CircleBezierDrawerCircumscribe end(float x, float y) {
    mEndInit.x = x;
    mEndInit.y = y;
    return this;
  }

  public CircleBezierDrawerCircumscribe withStartCircle() {
    mDrawStartCircle = true;
    return this;
  }

  public CircleBezierDrawerCircumscribe withEndCircle() {
    mDrawEndCircle = true;
    return this;
  }

  public CircleBezierDrawerCircumscribe withPoints() {
    mDrawPoints = true;
    return this;
  }

  public CircleBezierDrawerCircumscribe withAxiOffset(float axiOffset) {
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
    mStart.set(mStartInit.x, mStartInit.y);
    mEnd.set(mEndInit.x, mEndInit.y);
    int mode = getMode(mStart, mEnd);
    double xa, ya, xb, yb, xc, yc, xd, yd;
    double lRr = Math.sqrt(
      (mStart.x - mEnd.x) * (mStart.x - mEnd.x) + (mStart.y - mEnd.y) * (mStart.y - mEnd.y));


    //mapping to TR
    double startX = this.mStart.x;
    double startY = this.mStart.y;
    //translate
    this.mStart.x = 0;
    this.mStart.y = 0;
    this.mEnd.x -= this.mStartInit.x;
    this.mEnd.y -= this.mStartInit.y;
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
    //process as out
    {
      double alpha = Math.acos((mR - mr) / lRr);
      double beta = Math.atan2(mEnd.y - mStart.y, mEnd.x - mStart.x);
      double angle1 = Math.PI - (alpha + beta);
      double angle2 = alpha - beta;
      xa = mStart.x - mR * Math.cos(angle1);
      ya = mStart.y + mR * Math.sin(angle1);
      xb = mStart.x + mR * Math.cos(angle2);
      yb = mStart.y - mR * Math.sin(angle2);

      xc = mEnd.x - mr * Math.cos(angle1);
      yc = mEnd.y + mr * Math.sin(angle1);
      xd = mEnd.x + mr * Math.cos(angle2);
      yd = mEnd.y - mr * Math.sin(angle2);
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
        break;
      case MODE_BR:
        ya = -ya;
        yb = -yb;
        yc = -yc;
        yd = -yd;
        //  yC = -yC;
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
    }
    if (mDrawStartCircle) {
      canvas.drawCircle(mStartInit.x, mStartInit.y, mR, paint);
    }
    if (mDrawEndCircle) {
      canvas.drawCircle(mEndInit.x, mEndInit.y, mr, paint);
    }

    if (mDrawPoints) {
      canvas.drawText("a", (float) xa, (float) ya, paint);
      canvas.drawText("b", (float) xb, (float) yb, paint);
      canvas.drawText("c", (float) xc, (float) yc, paint);
      canvas.drawText("d", (float) xd, (float) yd, paint);
    }

    mTmpPath.reset();
    mTmpPath.moveTo((float) xa, (float) ya);
    mTmpPath.lineTo((float) xc, (float) yc);
    mTmpPath.lineTo((float) xd, (float) yd);
    mTmpPath.lineTo((float) xb, (float) yb);

    canvas.drawPath(mTmpPath, paint);

  }
}
