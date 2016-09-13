package owo.bom;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bezier extends View {

    private Paint paint;

    private Point[] controlPoints;

    private List<Point> drawPoints;

    private static final int pointNumber = 10;

    public Bezier(Context context) {
        this(context, null);
    }

    public Bezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        drawPoints = new ArrayList<Point>();
    }

    public void setColor(int color) {

        if ((color >> 24 & 0xFF) == 0) color = 0xFF000000 | color;

        paint.setColor(color);
    }


    public void setControlPoints(Point[] controlPoints) {
//        if (controlPoints != null && (controlPoints.length > 5 || controlPoints.length < 3))
//            throw new ArrayIndexOutOfBoundsException();
        this.controlPoints = controlPoints;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //近似值法按照趋近求值
//        if (controlPoints != null && controlPoints.length > 2) {
//            for (int i = 0; i <= pointNumber; i++) {
//                Point[] processPoints = controlPoints;
//                Point[] tmpProcessPoints = new Point[processPoints.length - 1];
//                while (tmpProcessPoints.length > 0) {
//                    for (int j = 0; j < tmpProcessPoints.length; j++) {
//                        Point t = null;
//                        t = getPointByRatio(processPoints[j], processPoints[j + 1], (float) i / (float) pointNumber);
//                        tmpProcessPoints[j] = t;
//                    }
//                    processPoints = tmpProcessPoints;
//                    tmpProcessPoints = new Point[processPoints.length - 1];
//                }
//                canvas.drawCircle(processPoints[0].x, processPoints[0].y, 5, paint);
//            }
//        }
        List list = new ArrayList();
        for (int i = 0; i < controlPoints.length; ++i) {
            list.add(controlPoints[i]);
        }
        draw(canvas, list);
    }

    public void draw(Canvas canvas, List<Point> controlPoints) {
        for (int i = 0; i < 8; ++i) {
            Point p = compute(controlPoints, i, 8);
            canvas.drawCircle(p.x, p.y, 5, paint);
        }
    }

    public Point compute(List<Point> controlPoints, int i, int n) {
        List<Point> tmpPoints = controlPoints;
        while (tmpPoints.size() > 2) {
            List<Point> points = new ArrayList<>();
            for (int j = 0; j < tmpPoints.size() - 1; j++) {
                Point start = tmpPoints.get(j);
                Point end = tmpPoints.get(j + 1);
                points.add(computePoint(start, end, i, n));
            }
            tmpPoints = points;
        }
        return computePoint(tmpPoints.get(0), tmpPoints.get(1), i, n);

    }

    public Point computePoint(Point start, Point end, int i, int n) {
        Point ret = new Point();
        ret.x = start.x + (end.x - start.x) * i / n;
        ret.y = start.y + (end.y - start.y) * i / n;
        return ret;
    }

    private static Point getPointByRatio(Point startPoint, Point endPoint, float ratio) {
        Point result = new Point();

        int dx = endPoint.x - startPoint.x;
        int dy = endPoint.y - startPoint.y;

        result.x = (int) (startPoint.x + (dx * ratio));
        result.y = (int) (startPoint.y + (dy * ratio));

        return result;
    }

    public static List<Point> points(Point[] controlPoints) {
        List<Point> points = new ArrayList<>();
        //近似值法按照趋近求值
        if (controlPoints != null && controlPoints.length > 2) {
            for (int i = 0; i <= pointNumber; i++) {
                Point[] processPoints = controlPoints;
                Point[] tmpProcessPoints = new Point[processPoints.length - 1];
                while (tmpProcessPoints.length > 0) {
                    for (int j = 0; j < tmpProcessPoints.length; j++) {
                        Point t = null;
                        t = getPointByRatio(processPoints[j], processPoints[j + 1], (float) i / (float) pointNumber);
                        tmpProcessPoints[j] = t;
                    }
                    processPoints = tmpProcessPoints;
                    tmpProcessPoints = new Point[processPoints.length - 1];
                }
                points.add(processPoints[0]);
            }
        }
        return points;
    }
}