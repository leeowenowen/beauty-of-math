package owo.bom.bezier;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;


public class BezierDrawer {
    private static final int MODE_TR = 1;
    private static final int MODE_TL = 2;
    private static final int MODE_BL = 3;
    private static final int MODE_BR = 4;

    private final PointF mStartInit = new PointF();
    private final PointF mEndInit = new PointF();
    private final PointF mStart = new PointF();
    private final PointF mEnd = new PointF();
    private float mR;
    private float mr;

    private float mAixOffset;
    private boolean mDrawStartCircle;
    private boolean mDrawEndCircle;
    private boolean mDrawPoints;

    private Path mTmpPath = new Path();

    public BezierDrawer() {
    }

    public BezierDrawer start(float x, float y) {
        mStartInit.x = x;
        mStartInit.y = y;
        return this;
    }

    public BezierDrawer R(float R) {
        mR = R;
        return this;
    }

    public BezierDrawer r(float r) {
        mr = r;
        return this;
    }

    public BezierDrawer end(float x, float y) {
        mEndInit.x = x;
        mEndInit.y = y;
        return this;
    }

    public BezierDrawer withStartCircle() {
        mDrawStartCircle = true;
        return this;
    }

    public BezierDrawer withEndCircle() {
        mDrawEndCircle = true;
        return this;
    }

    public BezierDrawer withPoints() {
        mDrawPoints = true;
        return this;
    }

    public BezierDrawer withAxiOffset(float axiOffset) {
        mAixOffset = axiOffset;
        return this;
    }

    private int getMode(PointF start, PointF end) {
        if (end.x >= start.x) {
            if (end.y >= start.y) {
                return MODE_TR;
            } else {
                return MODE_BR;
            }
        } else {
            if (end.y >= start.y) {
                return MODE_TL;
            } else {
                return MODE_BL;
            }
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        mStart.set(mStartInit.x, mStartInit.y);
        mEnd.set(mEndInit.x, mEndInit.y);
        int mode = getMode(mStart, mEnd);
        double xa, ya, xb, yb, xc, yc, xd, yd, xC, yC;
        double lRr = Math.sqrt(
                (mStart.x - mEnd.x) * (mStart.x - mEnd.x) + (mStart.y - mEnd.y) * (mStart.y - mEnd.y));

        double factor = mR / (mR + mr);
        double lR = lRr * factor;

        //mapping to TR
        double startX = this.mStart.x;
        double startY = this.mStart.y;
        //translate
        this.mStart.x = 0;
        this.mStart.y = 0;
        this.mEnd.x -= this.mStartInit.x;
        this.mEnd.y -= this.mStartInit.y;
        //map
        switch (mode) {
            case MODE_TR:
                break;
            case MODE_TL:
                this.mEnd.x = -this.mEnd.x;
                break;
            case MODE_BL:
                this.mEnd.x = -this.mEnd.x;
                this.mEnd.y = -this.mEnd.y;
                break;
            case MODE_BR:
                this.mEnd.y = -this.mEnd.y;
                break;
        }
        xC = mStart.x + Math.abs(mEnd.x - mStart.x) * factor;
        yC = mStart.y + Math.abs(mEnd.y - mStart.y) * factor;
        //process as in
        {
            double alpha = Math.acos(mR / lR);
            double beta = Math.atan2(yC - mStart.y, xC - mStart.x);
            xa = mStart.x + mR * Math.cos(alpha + beta);
            ya = mStart.y + mR * Math.sin(alpha + beta);
            xc = mStart.x + mR * Math.cos(alpha - beta);
            yc = mStart.y - mR * Math.sin(alpha - beta);


            xb = xc + (xC - xc) * lRr / lR;
            yb = yc + (yC - yc) * lRr / lR;

            xd = xa + (xC - xa) * lRr / lR;
            yd = ya + (yC - ya) * lRr / lR;
        }
        //de-map
        switch (mode) {
            case MODE_TR:
                break;
            case MODE_TL:
                xa = -xa;
                xc = -xc;
                xb = -xb;
                xd = -xd;
                xC = -xC;
                break;
            case MODE_BL:
                xa = -xa;
                ya = -ya;
                xb = -xb;
                yb = -yb;
                xc = -xc;
                yc = -yc;
                xd = -xd;
                yd = -yd;
                xC = -xC;
                yC = -yC;
                break;
            case MODE_BR:
                ya = -ya;
                yb = -yb;
                yc = -yc;
                yd = -yd;
                yC = -yC;
                break;
        }
        // de-translate
        {
            xa += startX;
            xb += startX;
            xc += startX;
            xd += startX;
            ya += startY;
            yb += startY;
            yc += startY;
            yd += startY;
            xC += startX;
            yC += startY;
        }
        if (mAixOffset > 0) {
            xa += mAixOffset;
            ya += mAixOffset;
            xb += mAixOffset;
            yb += mAixOffset;
            xc += mAixOffset;
            yc += mAixOffset;
            xd += mAixOffset;
            yd += mAixOffset;
            mStart.x += mAixOffset;
            mStart.y += mAixOffset;
            mEnd.x += mAixOffset;
            mEnd.y += mAixOffset;
            xC += mAixOffset;
            yC += mAixOffset;
        }
        if (mDrawStartCircle) {
            canvas.drawCircle(mStartInit.x, mStartInit.y, mR, paint);
        }
        if (mDrawEndCircle) {
            canvas.drawCircle(mEndInit.x, mEndInit.y, mr, paint);
        }

        if (mDrawPoints) {
            canvas.drawText("a", (float) xa, (float) ya, paint);
            canvas.drawText("b", (float) xb, (float) yb, paint);
            canvas.drawText("c", (float) xc, (float) yc, paint);
            canvas.drawText("d", (float) xd, (float) yd, paint);
            canvas.drawText("C", (float) xC, (float) yC, paint);
        }

        mTmpPath.reset();
        mTmpPath.moveTo((float) xa, (float) ya);
        mTmpPath.quadTo((float) xC, (float) yC, (float) xb, (float) yb);
        mTmpPath.moveTo((float) xc, (float) yc);
        mTmpPath.quadTo((float) xC, (float) yC, (float) xd, (float) yd);
        canvas.drawPath(mTmpPath, paint);

    }

}
