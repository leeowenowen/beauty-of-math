package owo.bom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangli on 16-9-11.
 */
public class CircleMarkView extends TextView {
    private static final String TAG = CircleMarkView.class.getSimpleName();

    public static interface onMarkChangedListener {
        void onMarkChanged();
    }

    private onMarkChangedListener markChangedListener;

    public void setOnMarkChangeListener(onMarkChangedListener listener) {
        markChangedListener = listener;
    }

    public CircleMarkView(Context context) {
        super(context);
    }

    public CircleMarkView(Context context, String mark) {
        super(context);
        setBackgroundColor(Color.TRANSPARENT);
        setText(mark);
        setGravity(Gravity.CENTER);
    }

    public int centerX() {
        return getLeft() + getWidth() / 2;
    }

    public int centerY() {
        return getTop() + getHeight() / 2;
    }

    private float origX = 0;
    private float origY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                origX = x;
                origY = y;
                Log.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(x, y);
                if (markChangedListener != null) {
                    markChangedListener.onMarkChanged();
                }
                Log.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                Log.d(TAG, "ACTION_UP");
            case MotionEvent.ACTION_OUTSIDE:
                getParent().requestDisallowInterceptTouchEvent(false);
                Log.d(TAG, "ACTION_OUTSIDE");
                break;
        }
        return true;
    }

    private void doMove(float x, float y) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int newLeftMargin = (int) (getLeft() + x - origX);
        int newTopMargin = (int) (getTop() + y - origY);
        if (newLeftMargin - mlp.leftMargin < 2 && newTopMargin - mlp.topMargin < 2) {
            return;
        }
        mlp.leftMargin = newLeftMargin;
        mlp.topMargin = newTopMargin;
        setLayoutParams(mlp);
        postInvalidate();
    }

    private Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.YELLOW);
        //paint.setTextSize(40);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        super.onDraw(canvas);
    }
}
