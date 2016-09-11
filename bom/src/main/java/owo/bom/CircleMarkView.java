package owo.bom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangli on 16-9-11.
 */
public class CircleMarkView extends TextView {
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
                origX = x;
                origY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(x, y);
                if (markChangedListener != null) {
                    markChangedListener.onMarkChanged();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void doMove(float x, float y) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) getLayoutParams();
        mlp.leftMargin = (int) (getLeft() + x - origX);
        mlp.topMargin = (int) (getTop() + y - origY);
        setLayoutParams(mlp);
    }

    private Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.RED);
        paint.setTextSize(40);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        super.onDraw(canvas);
    }
}
