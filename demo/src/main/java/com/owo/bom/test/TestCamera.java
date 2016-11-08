package com.owo.bom.test;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by wangli on 11/8/16.
 */

public class TestCamera extends View {
  private Paint mPaint = new Paint();

  public TestCamera(Context context) {
    super(context);
    mPaint.setColor(Color.RED);
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
  }

  public int mRotateX, mRotateY, mRotateZ, mTX, mTY, mTZ;
  private Camera mCamera = new Camera();

  public void setRX(int x) {
    mRotateX = x;
  }

  public void setRY(int y) {
    mRotateY = y;
  }

  public void setRZ(int z) {
    mRotateZ = z;
  }

  private Matrix mMatrix = new Matrix();

  @Override
  protected void onDraw(Canvas canvas) {
    mPaint.setColor(Color.GREEN);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, mPaint);
    mPaint.setColor(Color.RED);
    mCamera.save();
    mCamera.rotate(mRotateX, mRotateY, mRotateZ);
    mCamera.translate(mTX, mTY, mTZ);
    //    mCamera.getMatrix(mMatrix);
    //    mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
    //    mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
    mCamera.applyToCanvas(canvas);
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, mPaint);
    mCamera.restore();

  }
}
