package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.FrameLayout;


public class SampleBezierViewLevel1 extends FrameLayout {
    private static final String TAG = "SampleBezierView";
    private static final int CIRCLE_VIEW_WH = 100;
    private CircleMarkView mStartView;
    private CircleMarkView mEndView;
    private CircleMarkView mControlView;

    public SampleBezierViewLevel1(Context context) {
        super(context);
        setClipChildren(false);
        mStartView = new CircleMarkView(context, "S");
        mEndView = new CircleMarkView(context, "E");
        mControlView = new CircleMarkView(context, "C");


        mStartView.setOnMarkChangeListener(onMarkChangedListener);
        mEndView.setOnMarkChangeListener(onMarkChangedListener);
        mControlView.setOnMarkChangeListener(onMarkChangedListener);

        addView(mStartView, new LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        addView(mEndView, new LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
        addView(mControlView, new LayoutParams(CIRCLE_VIEW_WH, CIRCLE_VIEW_WH));
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
        super.dispatchDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path.reset();
        paint.setColor(Color.GREEN);
        path.moveTo(mStartView.centerX(), mStartView.centerY());
        path.quadTo(mControlView.centerX(), mControlView.centerY(), mEndView.centerX(), mEndView.centerY());
        canvas.drawPath(path, paint);

    }
}
