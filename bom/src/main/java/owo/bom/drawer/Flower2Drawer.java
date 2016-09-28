package owo.bom.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import owo.bom.splash.BaseDrawer;

/**
 * Created by wangli on 16-9-22.
 */
public class Flower2Drawer extends BaseDrawer {
    private float xo;
    private float yo;
    private float R;
    private float r;
    private int n;


    public Flower2Drawer(PointF center) {
        xo = center.x;
        yo = center.y;
    }

    public void setROut(float r) {
        R = r;
    }

    public void setRIn(float r) {
        this.r = r;
    }

    public void setN(int n) {
        this.n = n;
    }

    private Path path = new Path();

    @Override
    public void draw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
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


//            path.moveTo(xs, ys);
//            path.quadTo(xor, yor, xe, ye);
//
            path.moveTo(xor, yor);
            path.cubicTo(xs, ys, xe, ye, xor, yor);

//            path.moveTo(xor, yor);
//            path.quadTo(xs, ys, xe, ye);

            canvas.drawPath(path, mPaint);

            xs = xe;
            ys = ye;
        }
    }
}
