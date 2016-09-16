package owo.bom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

public class WaterDropView extends View {
  private Paint mPaint = new Paint();

  public WaterDropView(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setStrokeWidth(1);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setTextSize(23.0f);
  }

  private boolean isDown = false;
  private float lastX;
  private float lastY;

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        isDown = true;
        break;
      case MotionEvent.ACTION_MOVE:
        float x = event.getX();
        float y = event.getY();
        x -= 500;
        y -= 500;
        mEnd = new PointF(x, y);
        invalidate();
        break;
      case MotionEvent.ACTION_UP:
        isDown = false;
        break;
      default:
        break;
    }
    return true;
  }

  private int width = 800;
  private int height = 800;

  private void drawAxis(Canvas canvas) {
    double offset = 100;
    double centerX = width / 2 + offset;
    double centerY = height / 2 + offset;
    double leftX = 0 + offset;
    double leftY = height / 2 + offset;
    double rightX = width + offset;
    double rightY = height / 2 + offset;
    double topX = width / 2 + offset;
    double topY = 0 + offset;
    double bottomX = width / 2 + offset;
    double bottomY = height + offset;
    double angle = Math.PI / 4;

    drawArrowBR(canvas, 50, angle, leftX, leftY, rightX, rightY);
    drawArrowBR(canvas, 50, angle, bottomX, bottomY, topX, topY);
    canvas.drawText("O", (float) centerX, (float) centerY, mPaint);
    canvas.drawText("Y", (float) topX, (float) topY, mPaint);
    canvas.drawText("X", (float) rightX, (float) rightY, mPaint);
  }

  private void drawArrowBR(Canvas canvas,
                           double r,
                           double angle,
                           double xs,
                           double ys,
                           double xe,
                           double ye) {
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

  private static final int MODE_LEFT = 0;
  private static final int MODE_RIGHT = 1;
  private static final int MODE_TOP = 2;
  private static final int MODE_BOTTOM = 3;

  private void drawWaterDrop(int mode,
                             Canvas canvas,
                             PointF pR,
                             PointF pr,
                             float R,
                             float r,
                             float aixOffset) {
    double xa = 0;
    double ya = 0;
    double xb = 0;
    double yb = 0;
    double xc = 0;
    double yc = 0;
    double xd = 0;
    double yd = 0;

    double xc1;
    double yc1;

    double lRr = Math.sqrt((pR.x - pr.x) * (pR.x - pr.x) + (pR.y - pr.y) * (pR.y - pr.y));

    double factor = R / (R + r);
    xc1 = pR.x + Math.abs(pr.x - pR.x) * factor;
    yc1 = pR.y + Math.abs(pr.y - pR.y) * factor;
    double lR = lRr * factor;
    double lr = lRr - lR;

    switch (mode) {
      case MODE_LEFT: {
      }
      break;
      case MODE_RIGHT: {
        double alpha = Math.acos(R / lR);
        double beta = Math.atan2(yc1 - pR.y, xc1 - pR.x);
        xa = pR.x + R * Math.cos(alpha + beta);
        ya = pR.y + R * Math.sin(alpha + beta);
        xc = pR.x + R * Math.cos(alpha - beta);
        yc = pR.y - R * Math.sin(alpha - beta);


        xb = xc + (xc1 - xc) * lRr / lR;
        yb = yc + (yc1 - yc) * lRr / lR;

        xd = xa + (xc1 - xa) * lRr / lR;
        yd = ya + (yc1 - ya) * lRr / lR;

      }
      break;
      case MODE_TOP: {
      }
      break;
      case MODE_BOTTOM: {
      }
      break;
    }

    {
      xa += aixOffset;
      ya += aixOffset;
      xb += aixOffset;
      yb += aixOffset;
      xc += aixOffset;
      yc += aixOffset;
      xd += aixOffset;
      yd += aixOffset;
      pR.x += aixOffset;
      pR.y += aixOffset;
      pr.x += aixOffset;
      pr.y += aixOffset;
      xc1 += aixOffset;
      yc1 += aixOffset;
    }
    canvas.drawCircle(pR.x, pR.y, R, mPaint);
    canvas.drawCircle(pr.x, pr.y, r, mPaint);

    canvas.drawText("a", (float) xa, (float) ya, mPaint);
    canvas.drawText("b", (float) xb, (float) yb, mPaint);
    canvas.drawText("c", (float) xc, (float) yc, mPaint);
    canvas.drawText("d", (float) xd, (float) yd, mPaint);
    canvas.drawText("C", (float) xc1, (float) yc1, mPaint);

    mTmpPath.reset();
    mTmpPath.moveTo((float) xa, (float) ya);
    mTmpPath.quadTo((float) xc1, (float) yc1, (float) xb, (float) yb);
    mTmpPath.moveTo((float) xc, (float) yc);
    mTmpPath.quadTo((float) xc1, (float) yc1, (float) xd, (float) yd);
    canvas.drawPath(mTmpPath, mPaint);

  }

  private Path mTmpPath = new Path();

  private void drawLine(Canvas canvas, double x1, double y1, double x2, double y2, Paint paint) {
    x1 = Math.abs(x1);
    y1 = Math.abs(y1);
    x2 = Math.abs(x2);
    y2 = Math.abs(y2);
    canvas.drawLine((float) x1, (float) y1, (float) x2, (float) y2, paint);
  }

  private PointF mStart = new PointF(0, 0);
  private PointF mEnd = new PointF(300, 300);
  private float mR = 100;
  private float mr = 50;
  private float mAixOffset = 500;

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawAxis(canvas);
    drawWaterDrop(MODE_RIGHT,
                  canvas,
                  new PointF(mStart.x, mStart.y),
                  new PointF(mEnd.x, mEnd.y),
                  mR,
                  mr,
                  mAixOffset);
    //drawWaterDrop(MODE_RIGHT, canvas, new PointF(0, 0), new PointF(300, 300), 100, 50, 500);
  }
}
