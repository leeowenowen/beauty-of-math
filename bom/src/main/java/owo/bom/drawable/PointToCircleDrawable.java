package owo.bom.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;

/**
 * Created by wangli on 9/15/16.
 */
public class PointToCircleDrawable extends ShapeDrawable {
  private GradientDrawable mDrawable;
  private float radius;
  private View v;


  public void setRadius(float radius) {
    this.radius = radius;
    v.invalidate();
  }

  public PointToCircleDrawable(View v) {
    this.v = v;
    //    mDrawable = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[] {
    //      Color.argb(255, 255, 0, 0), Color.argb(255, 150, 0, 255), Color.argb(255, 50, 255, 0)
    //    });
    //
    //    mDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
    //      0xFFFF0000, 0xFF00FF00, 0xFF0000FF
    //    });
    //    mDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
    int strokeWidth = 5; // 3dp 边框宽度
    int roundRadius = 15; // 8dp 圆角半径
    int strokeColor = Color.parseColor("#2E3135");//边框颜色
    int fillColor = Color.parseColor("#DFDFE0");//内部填充颜色


    ///  mDrawable.setColor(fillColor);
    //mDrawable.setCornerRadius(roundRadius);
    //  mDrawable.setStroke(strokeWidth, strokeColor);
    // mDrawable.setShape(GradientDrawable.RING);
    //  mDrawable.setCornerRadii(new float[] {5, 0, 5, 0});
    //setImageDrawable(mDrawable);
    //UIUtil.setBackgroundDrawable(this, mDrawable);
    setShape(new Shape() {
      @Override
      public void draw(Canvas canvas, Paint paint) {
        float w = getWidth();
        float h = getHeight();
        paint.setColor(Color.RED);
        canvas.drawCircle(w / 2, h / 2, radius, paint);
      }
    });
  }

  /*
  Paint p=new Paint();
  LinearGradient lg=new LinearGradien(0,0,100,100,Color.RED,Color.BLUE,Shader.TileMode.MIRROR);
  参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点，最后参数为平铺方式，这里设置为镜像
  Gradient是基于Shader类，所以我们通过Paint的setShader方法来设置这个渐变，代码如下:
  mPaint.setShader(lg);
  canvas.drawCicle(0,0,200,mPaint); //参数3为画圆的半径，类型为float型。

  它除了定义开始颜色和结束颜色以外还可以定义，多种颜色组成的分段渐变效果
  LinearGradient shader = new LinearGradient(0, 0, endX, endY, new int[]{startColor, midleColor, endColor},new float[]{0 , 0.5f, 1.0f}, TileMode.MIRROR);
  其中参数new int[]{startColor, midleColor, endColor}是参与渐变效果的颜色集合，
  其中参数new float[]{0 , 0.5f, 1.0f}是定义每个颜色处于的渐变相对位置，
  这个参数可以为null，如果为null表示所有的颜色按顺序均匀的分布
   */
  private Paint mPaint = new Paint();

}
