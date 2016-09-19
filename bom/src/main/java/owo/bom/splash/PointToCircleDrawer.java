package owo.bom.splash;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by wangli on 9/15/16.
 */
public class PointToCircleDrawer extends BaseDrawer {
  private GradientDrawable mDrawable;
  private float radius;
  private PointF center;

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public PointToCircleDrawer(final PointF center) {
    this.center = center;
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.drawCircle(center.x, center.y, radius, mPaint);
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

}
