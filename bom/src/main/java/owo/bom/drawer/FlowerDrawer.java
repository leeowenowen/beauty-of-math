package owo.bom.drawer;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

import owo.bom.splash.BaseDrawer;
import owo.bom.splash.Drawer;

/**
 * Created by wangli on 16-9-22.
 */
public class FlowerDrawer extends BaseDrawer {
    private float xo;
    private float yo;
    private float R;
    private float r;
    private int n;


    public FlowerDrawer(PointF center) {
        center(center);
    }

    public FlowerDrawer center(PointF center) {
        xo = center.x;
        yo = center.y;
        return this;
    }

    public FlowerDrawer setROut(float r) {
        R = r;
        return this;
    }

    public FlowerDrawer setRIn(float r) {
        this.r = r;
        return this;
    }

    public FlowerDrawer setN(int n) {
        this.n = n;
        return this;
    }

    private Path path = new Path();

    @Override
    public void draw(Canvas canvas) {
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
