package owo.bom;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

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
        if (controlPoints != null && (controlPoints.length > 5 || controlPoints.length < 3))
            throw new ArrayIndexOutOfBoundsException();
        this.controlPoints = controlPoints;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
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
                canvas.drawCircle(processPoints[0].x, processPoints[0].y, 5, paint);
            }
        }
    }

    private Point getPointByRatio(Point startPoint, Point endPoint, float ratio) {
        Point result = new Point();

        int dx = endPoint.x - startPoint.x;
        int dy = endPoint.y - startPoint.y;

        result.x = (int) (startPoint.x + (dx * ratio));
        result.y = (int) (startPoint.y + (dy * ratio));

        return result;
    }
}