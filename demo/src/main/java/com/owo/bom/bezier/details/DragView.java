package com.owo.bom.bezier.details;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.owo.bom.base.SelfDrawView;

import owo.bom.drawer.CircleBezierDrawer;

/**
 * Created by wangli on 16-10-25.
 */
public class DragView extends SelfDrawView {
    private static final String TAG = DragView.class.getSimpleName();
    private CircleBezierDrawer mDrawer = new CircleBezierDrawer();

    public DragView(Context context) {
        super(context);
    }

    private float lastX = 0;
    private float lastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - lastX) > 5 || Math.abs(y - lastY) > 5) {
                    lastX = x;
                    lastY = y;
                    invalidate();
                }
                Log.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP");
            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "ACTION_OUTSIDE");
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        float endX = (lastX == 0 ? getWidth() * 3 / 4 : lastX);
        float endY = (lastY == 0 ? getHeight() * 3 / 4 : lastY);
        mDrawer.R(getWidth() / 8).r(getWidth() / 10).start(getWidth() / 2, getHeight() / 2).end(endX, endY).withPoints().withStartCircle().withEndCircle();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mDrawer.draw(canvas, mPaint);
    }
}
