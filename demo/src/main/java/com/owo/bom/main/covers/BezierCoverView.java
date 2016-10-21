package com.owo.bom.main.covers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.Log;
import android.view.Gravity;
import android.widget.FrameLayout;

import owo.bom.CircleMarkView;

/**
 * Created by wangli on 16-10-20.
 */
public class BezierCoverView extends FrameLayout {
    private static final String TAG = BezierCoverView.class.getSimpleName();
    private static final int CIRCLE_VIEW_WH = 100;
    private CircleMarkView mStartView;
    private CircleMarkView mEndView;
    private CircleMarkView mControl1View;
    private CircleMarkView mControl2View;

    public BezierCoverView(Context context) {
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

        FrameLayout.LayoutParams slp = new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH, Gravity.LEFT | Gravity.TOP);
        slp.leftMargin = 20;
        slp.topMargin = 20;
        addView(mStartView, slp);
        FrameLayout.LayoutParams elp = new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH, Gravity.RIGHT | Gravity.BOTTOM);
        elp.rightMargin = 20;
        elp.bottomMargin = 20;
        addView(mEndView, elp);
        FrameLayout.LayoutParams c1lp = new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH, Gravity.LEFT | Gravity.BOTTOM);
        c1lp.leftMargin = 150;
        c1lp.bottomMargin = 50;
        addView(mControl1View, c1lp);
        FrameLayout.LayoutParams c2lp = new FrameLayout.LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH, Gravity.RIGHT | Gravity.TOP);
        c2lp.rightMargin = 150;
        c2lp.topMargin = 50;
        addView(mControl2View, c2lp);
    }

    private CircleMarkView.onMarkChangedListener onMarkChangedListener = new CircleMarkView.onMarkChangedListener() {
        @Override
        public void onMarkChanged() {
            Log.d(TAG, "onMarkChangedListener");
            postInvalidate();
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
        PathEffect pathEffect = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        paint.setPathEffect(pathEffect);
        path.reset();
        path.moveTo(mControl1View.centerX(), mControl1View.centerY());
        path.lineTo(mControl2View.centerX(), mControl2View.centerY());
        canvas.drawPath(path, paint);
        path.reset();
        paint.setColor(Color.GREEN);
        paint.setPathEffect(null);
        path.moveTo(mStartView.centerX(), mStartView.centerY());
        path.cubicTo(mControl1View.centerX(), mControl1View.centerY(), mControl2View.centerX(), mControl2View.centerY(), mEndView.centerX(), mEndView.centerY());
        canvas.drawPath(path, paint);
        super.dispatchDraw(canvas);
    }
}