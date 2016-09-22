package owo.bom.splash;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

import owo.bom.drawer.CircleBezierDrawer;

/**
 * Created by wangli on 9/17/16.
 */
public class CircleToCrossDrawer extends BaseDrawer {
    CircleBezierDrawer mLefDrawer = new CircleBezierDrawer();
    CircleBezierDrawer mRightDrawer = new CircleBezierDrawer();
    CircleBezierDrawer mTopDrawer = new CircleBezierDrawer();
    CircleBezierDrawer mBottomDrawer = new CircleBezierDrawer();

    private PointF mRCenter;

    public CircleToCrossDrawer(PointF circleCenter) {
        mRCenter = circleCenter;
        mLefDrawer.start(mRCenter.x, mRCenter.y).withStartCircle().withEndCircle();
        mRightDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();
        mTopDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();
        mBottomDrawer.start(mRCenter.x, mRCenter.y).withEndCircle();

        mPaint.setColor(Color.RED);
        //  mPaint.setStrokeWidth(1);
        // mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(23.0f);
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
    }


    @Override
    public void draw(Canvas canvas) {
        mLefDrawer.draw(canvas, mPaint);
        mRightDrawer.draw(canvas, mPaint);
        mTopDrawer.draw(canvas, mPaint);
        mBottomDrawer.draw(canvas, mPaint);
    }
}
