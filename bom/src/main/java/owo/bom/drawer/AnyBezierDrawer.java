package owo.bom.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import owo.bom.splash.BaseDrawer;
import owo.bom.util.RandomUtil;

/**
 * Created by wangli on 16-9-23.
 */
public class AnyBezierDrawer extends BaseDrawer {

    private List<Point> controlPoints = new ArrayList();
    private List<Integer> colors;
    private int n;
    private int i;

    public AnyBezierDrawer() {
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setControlPoints(List<Point> points, List<Integer> colors) {
        controlPoints = points;
        this.colors = colors;
        n = controlPoints.size();
    }

    @Override
    public void draw(Canvas canvas) {
        //  for (int k = 0; k < i; k++)
        int k = i;
        {
            Point p = compute(canvas, controlPoints, k, n);
            drawPoint(canvas, p.x, p.y, 10, colors.get(0));
        }
    }

    private void drawPoint(Canvas canvas, int x, int y, int r, int color) {
        int old = mPaint.getColor();
        mPaint.setColor(color);
        canvas.drawCircle(x, y, r, mPaint);
        mPaint.setColor(old);
    }

    public Point compute(Canvas canvas, List<Point> controlPoints, int k, int n) {
        List<Point> tmpPoints = controlPoints;
        while (tmpPoints.size() > 2) {
            drawPoints(canvas, tmpPoints, colors.get(tmpPoints.size() - 1));
            List<Point> points = new ArrayList<>();
            for (int j = 0; j < tmpPoints.size() - 1; j++) {
                Point start = tmpPoints.get(j);
                Point end = tmpPoints.get(j + 1);
                points.add(computePoint(start, end, k, n));
            }
            tmpPoints = points;
        }
        return computePoint(tmpPoints.get(0), tmpPoints.get(1), k, n);

    }

    public Point computePoint(Point start, Point end, int k, int n) {
        Point ret = new Point();
        ret.x = start.x + (end.x - start.x) * k / n;
        ret.y = start.y + (end.y - start.y) * k / n;
        return ret;
    }

    private Path path = new Path();

    public void drawPoints(Canvas canvas, List<Point> points, int color) {
        int old = mPaint.getColor();
        mPaint.setColor(color);
        path.reset();
        Iterator<Point> iterator = points.iterator();
        Point next = iterator.next();
        path.moveTo(next.x, next.y);
        drawPoint(canvas, next.x, next.y, 5, color);
        while (iterator.hasNext()) {
            Point point = iterator.next();
            path.lineTo(point.x, point.y);
        }
        canvas.drawPath(path, mPaint);
        mPaint.setColor(old);
    }
}
