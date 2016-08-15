package owo.bom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;


public class VerticalView extends View {
    private Paint mPaint = new Paint();
    Bitmap mMemBitmap;
    private Canvas mMemCanvas;
    private Path mPath = new Path();

    public VerticalView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mMemBitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
    }

    /**
     * 令(y-y1)/(y2-y1)=(x-x1)/(x2-x1)=t（t为参数）
     * y=(y2-y1)t+y1（t为参数）
     * x=(x2-x1)t+x1
     */
    private void drawVertical(Canvas canvas) {
        double x0 = 0;
        double y0 = 0;
        double x1 = 100;
        double y1 = 100;
        double t = (y0 - y1) / (x0 - x1);
        double k = -1 / t;
        

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVertical(mMemCanvas);
        canvas.drawBitmap(mMemBitmap, 0, 0, null);
    }
}
