package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;


public class SampleBezierView extends View {
    private Paint mPaint = new Paint();

    public SampleBezierView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    /**
     * x=sin(t)*sin(t)*cos(t)
     * y=cos(t)*cos(t)*sin(t)
     */
    private void drawHeart(Canvas canvas) {

    }

    Path path = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        path.reset();
        path.moveTo(100, 100);
        path.quadTo(100, 200, 100, 200);
        canvas.drawPath(path, mPaint);


    }
}
