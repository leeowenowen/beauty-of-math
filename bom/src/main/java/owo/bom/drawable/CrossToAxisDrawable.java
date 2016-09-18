package owo.bom.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.View;

/**
 * Created by wangli on 16-9-18.
 */
public class CrossToAxisDrawable extends BaseDrawable {
    private Paint mPaint = new Paint();
    private float centerX;
    private float centerY;
    private float arrowOffset;
    private float angle;
    private float arrowLength;

    public CrossToAxisDrawable(PointF center) {
        centerX = center.x;
        centerY = center.y;
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(23.0f);
    }

    public void setArrowOffset(float arrowOffset) {
        this.arrowOffset = arrowOffset;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setArrowLength(float arrowLength) {
        this.arrowLength = arrowLength;
    }

    @Override
    public void draw(Canvas canvas) {
        double leftX = centerX - arrowOffset;
        double leftY = centerY;
        double rightX = centerX + arrowOffset;
        double rightY = centerY;
        double topX = centerX;
        double topY = centerY - arrowOffset;
        double bottomX = centerX;
        double bottomY = centerY + arrowOffset;

        drawArrow(canvas, arrowLength, angle, leftX, leftY, rightX, rightY);
        drawArrow(canvas, arrowLength, angle, bottomX, bottomY, topX, topY);
        canvas.drawText("O", (float) centerX, (float) centerY, mPaint);
        canvas.drawText("Y", (float) topX, (float) topY, mPaint);
        canvas.drawText("X", (float) rightX, (float) rightY, mPaint);
    }

    private void drawArrow(Canvas canvas, double r, double angle, double xs, double ys, double xe, double ye) {
        double angle2 = Math.atan2(ye - ys, xe - xs);
        angle2 = -angle2;
        double x2 = xe - r * Math.cos(angle + angle2);
        double y2 = ye + r * Math.sin(angle + angle2);

        double x3 = xe - r * Math.cos(angle2 - angle);
        double y3 = ye + r * Math.sin(angle2 - angle);


        drawLine(canvas, xs, ys, xe, ye, mPaint);
        drawLine(canvas, x2, y2, xe, ye, mPaint);
        drawLine(canvas, x3, y3, xe, ye, mPaint);
    }

    private void drawLine(Canvas canvas, double x1, double y1, double x2, double y2, Paint paint) {
        x1 = Math.abs(x1);
        y1 = Math.abs(y1);
        x2 = Math.abs(x2);
        y2 = Math.abs(y2);
        canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
    }
}
