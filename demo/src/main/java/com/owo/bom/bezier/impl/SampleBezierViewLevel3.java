package com.owo.bom.bezier.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.FrameLayout;

import owo.bom.CircleMarkView;


public class SampleBezierViewLevel3 extends FrameLayout {
    private static final String TAG = "SampleBezierView";
    private static final int CIRCLE_VIEW_WH = 200;
    private CircleMarkView mStartView;
    private CircleMarkView mEndView;
    private CircleMarkView mControl1View;
    private CircleMarkView mControl2View;

    public SampleBezierViewLevel3(Context context) {
        super(context);
        setClipChildren(false);
        mStartView = new CircleMarkView(context, "S");
        mEndView = new CircleMarkView(context, "E");
        mControl1View = new CircleMarkView(context, "C1");
        mControl2View = new CircleMarkView(context, "C2");

        mStartView.setOnMarkChangeListener(onMarkChangedListener);
        mEndView.setOnMarkChangeListener(onMarkChangedListener);
        mControl1View.setOnMarkChangeListener(onMarkChangedListener);
        mControl2View.setOnMarkChangeListener(onMarkChangedListener);

        addView(mStartView, new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        addView(mEndView, new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        addView(mControl1View, new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        addView(mControl2View, new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        setBackgroundColor(Color.BLACK);
    }

    private CircleMarkView.onMarkChangedListener onMarkChangedListener = new CircleMarkView.onMarkChangedListener() {
        @Override
        public void onMarkChanged() {
            invalidate();
        }
    };
    private Path path = new Path();
    private Paint paint = new Paint();

    @Override
    protected void dispatchDraw(Canvas canvas) {
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        path.reset();
        path.moveTo(mControl1View.centerX(), mControl1View.centerY());
        path.lineTo(mControl2View.centerX(), mControl2View.centerY());
        canvas.drawPath(path, paint);
        path.reset();
        paint.setColor(Color.GREEN);
        path.moveTo(mStartView.centerX(), mStartView.centerY());
        path.cubicTo(mControl1View.centerX(), mControl1View.centerY(), mControl2View.centerX(), mControl2View.centerY(), mEndView.centerX(), mEndView.centerY());
        canvas.drawPath(path, paint);
        super.dispatchDraw(canvas);
    }

    public CircleMarkView start() {
        return mStartView;
    }

    public CircleMarkView end() {
        return mEndView;
    }

    public CircleMarkView controlPoint1() {
        return mControl1View;
    }

    public CircleMarkView controlPoint2() {
        return mControl2View;
    }
}
