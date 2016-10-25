package com.owo.bom.bezier.details;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;

import com.owo.bom.base.SelfDrawView;

import owo.bom.drawer.CircleBezierDrawer;
import owo.bom.drawer.FlowerDrawer;

/**
 * Created by wangli on 16-10-25.
 */
public class FlowerView extends SelfDrawView {
    private static final String TAG = FlowerView.class.getSimpleName();
    private FlowerDrawer mDrawer = new FlowerDrawer(null);

    public FlowerView(Context context) {
        super(context);
    }

    private float lastX = 0;
    private float lastY = 0;
    int n = 1;

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
                    if (x > lastX || y > lastY) {
                        n++;
                    } else {
                        n--;
                    }
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
        mDrawer.center(new PointF(getWidth() / 2, getHeight() / 2)).
                setN(n).setRIn(getWidth() / 8).setROut(getWidth() / 3);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mDrawer.draw(canvas);
    }
}
