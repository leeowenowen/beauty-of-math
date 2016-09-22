package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by wangli on 16-9-22.
 */
public class GrassView extends View {
    private Path path = new Path();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GrassView(Context context) {
        super(context);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getWidth() == 0) return;

//        for (float i = 0; i <= getHeight(); i += 80) {
//            float xo = getWidth() / 2;
//            float yo = 0;
//            float y = i;
//            float xl = getWidth() * i / (2 * getHeight());
//            float xC = xo;
//            float xr = getWidth() - xl;
//            path.reset();
//            path.moveTo(xo, yo);
//            path.quadTo(xC + xl, y, xl, y);
//            canvas.drawPath(path, mPaint);
//            path.reset();
//            path.moveTo(xo, yo);
//            path.quadTo(xC - xl, y, xr, y);
//            canvas.drawPath(path, mPaint);
//
//        }


//        path.reset();
//        path.moveTo(getWidth() / 2, 0);
//        path.cubicTo(getWidth(), getHeight(), 0, getHeight(), getWidth() / 2, 0);
//        canvas.drawPath(path, mPaint);

        float xo = 500;
        float yo = 500;
        float R = 500;
        float r = 0;
        int n = 6;
        double sec = Math.PI * 2 / n;
        float xs = xo + R;
        float ys = yo;
        for (int i = 1; i <= n; i++) {
            double a = sec * i;
            float xe = (float) (xo + R * Math.cos(a));
            float ye = (float) (yo + R * Math.sin(a));

            float xor = xo + ((xe + xs) / 2 - xo) * r / (R + r);
            float yor = yo + ((ye + ys) / 2 - yo) * r / (R + r);

            path.reset();
            path.moveTo(xor, yor);
            path.cubicTo(xs, ys, xe, ye, xor, yor);
            canvas.drawPath(path, mPaint);

            xs = xe;
            ys = ye;
        }
    }
}
