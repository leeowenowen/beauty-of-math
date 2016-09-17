package owo.bom.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

import owo.bom.bezier.BezierDrawer;

/**
 * Created by wangli on 9/17/16.
 */
public class CircleToAxiDrawable extends BaseDrawable {
  BezierDrawer mLefDrawer = new BezierDrawer();
  BezierDrawer mRightDrawer = new BezierDrawer();
  BezierDrawer mTopDrawer = new BezierDrawer();
  BezierDrawer mBottomDrawer = new BezierDrawer();

  private PointF mRCenter;
  private View target;

  public CircleToAxiDrawable(View v, PointF circleCenter) {
    mRCenter = circleCenter;
    mLefDrawer.start(mRCenter.x, mRCenter.y).withStartCircle().withPoints().withEndCircle();
    mRightDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();
    mTopDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();
    mBottomDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();

    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(1);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setTextSize(23.0f);
    target = v;
  }

  public void setCircleCenterDistance(float RrDistance) {
    mLefDrawer.end(mRCenter.x - RrDistance, mRCenter.y);
    mRightDrawer.end(mRCenter.x + RrDistance, mRCenter.y);
    mTopDrawer.end(mRCenter.x, mRCenter.y - RrDistance);
    mBottomDrawer.end(mRCenter.x, mRCenter.y + RrDistance);
  }

  public void setStartRadius(float R) {
    mLefDrawer.R(R);
    mRightDrawer.R(R);
    mTopDrawer.R(R);
    mBottomDrawer.R(R);
  }

  public void setEndRadius(float r) {
    mLefDrawer.r(r);
    mRightDrawer.r(r);
    mTopDrawer.r(r);
    mBottomDrawer.r(r);
    target.invalidate();
  }


  @Override
  public void draw(Canvas canvas) {
    mLefDrawer.draw(canvas, mPaint);
//    mRightDrawer.draw(canvas, mPaint);
//    mTopDrawer.draw(canvas, mPaint);
//    mBottomDrawer.draw(canvas, mPaint);
  }
}
