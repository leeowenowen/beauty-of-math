package com.owo.bom.bezier.details;

/**
 * Created by wangli on 7/19/16.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

public class PageTurnView extends View {
  //memory buffer
  private Bitmap mBackground = null;
  private Canvas mMemCanvas = null;
  //pages to draw
  private Bitmap mCurPage = null;
  private Bitmap mCurPageBack = null;
  private Bitmap mNextPage = null;
  //touch point
  private PointF mPointA = new PointF();//Touch point
  private PointF mPointB = new PointF();
  private PointF mPointC = new PointF();
  private PointF mPointD = new PointF();
  private PointF mPointE = new PointF();
  private PointF mPointF = new PointF();
  private PointF mPointG = new PointF();
  private PointF mPointH = new PointF();
  private PointF mPointI = new PointF();
  private PointF mPointJ = new PointF();
  private PointF mPointK = new PointF();
  private PointF mPointM = new PointF();
  private PointF mPointN = new PointF();
  private PointF mPointBCMid = new PointF();
  private PointF mPointJKMid = new PointF();

  private PointF mPointRotateCenter = new PointF();
  private float mRotateDegree = 0.0f;
  //rotate and translate matrix
  private Matrix mMatrix = new Matrix();

  //right angle ratio, ce = ef*(1-ratio), ap = ag * ratio
  private float mRightAngleRatio = 0.5f;

  private String mStrCur = new String();
  private String mStrCurBack = new String();
  private String mStrNext = new String();

  private TextPaint mTextPaint = new TextPaint();
  private TextPaint mPointPaint = new TextPaint();

  //width and height of book
  private static int kWidth = 600;
  private static int kHeight = 700;
  //width and height of canvas(including book and all the points show area)
  private static int kCanvasWidth = 800;
  private static int kCanvasHeight = 1024;

  public PageTurnView(Context context) {
    super(context);
  }

  public PageTurnView(Context context, int w, int h) {
    super(context);
    setup(context, w, h);
  }

  private void setup(Context context, int w, int h) {
    w = w * 2 / 3;
    h = h * 2 / 3;

    kWidth = w;
    kHeight = h;
    kCanvasHeight = h;
    kCanvasWidth = w;

    //initialize background and default points position
    mBackground = Bitmap.createBitmap(kCanvasWidth, kCanvasHeight, Bitmap.Config.ARGB_8888);
    mMemCanvas = new Canvas(mBackground);

    mPointA.x = kWidth;
    mPointA.y = kHeight;
    mPointF.x = kWidth;
    mPointF.y = kHeight;

    //make page text, if you want to show page text, uncomment lines below
    //    for (int i = 0; i < 1000; i++) {
    //      mStrCur += "AAAAAAAA";
    //      mStrCurBack += "BBBBBBBB";
    //      mStrNext += "CCCCCCCC";
    //    }
    //initialize text paint and point paint
    mTextPaint.setColor(Color.WHITE);
    mTextPaint.setTextSize(28);

    mPointPaint.setColor(Color.BLACK);
    mPointPaint.setTextSize(35);
    mPointPaint.setFakeBoldText(true);

    mCurPage = Bitmap.createBitmap(kWidth, kHeight, Bitmap.Config.ARGB_8888);
    Canvas tmpCanvas = new Canvas(mCurPage);
    tmpCanvas.drawColor(Color.GREEN);
    StaticLayout layout = new StaticLayout(mStrCur,
                                           mTextPaint,
                                           kWidth,
                                           Layout.Alignment.ALIGN_NORMAL,
                                           1.0F,
                                           0.0F,
                                           true);
    layout.draw(tmpCanvas);

    mCurPageBack = Bitmap.createBitmap(kWidth, kHeight, Bitmap.Config.ARGB_8888);
    tmpCanvas = new Canvas(mCurPageBack);
    tmpCanvas.drawColor(Color.RED);
    layout = new StaticLayout(mStrCurBack,
                              mTextPaint,
                              kWidth,
                              Layout.Alignment.ALIGN_NORMAL,
                              1.0F,
                              0.0F,
                              true);
    layout.draw(tmpCanvas);

    mNextPage = Bitmap.createBitmap(kWidth, kHeight, Bitmap.Config.ARGB_8888);
    tmpCanvas = new Canvas(mNextPage);
    tmpCanvas.drawColor(Color.BLUE);
    layout = new StaticLayout(mStrNext,
                              mTextPaint,
                              kWidth,
                              Layout.Alignment.ALIGN_NORMAL,
                              1.0F,
                              0.0F,
                              true);
    layout.draw(tmpCanvas);
  }

  private void JudgePointF() {
    if (mPointA.x < kWidth / 2 && mPointA.y < kHeight / 2) {
      mPointF.x = 0;
      mPointF.y = 0;
    } else if (mPointA.x >= kWidth / 2 && mPointA.y < kHeight / 2) {
      mPointF.x = kWidth;
      mPointF.y = 0;
    } else if (mPointA.x < kWidth / 2 && mPointA.y >= kHeight / 2) {
      mPointF.x = 0;
      mPointF.y = kHeight;
    } else if (mPointA.x >= kWidth / 2 && mPointA.y >= kHeight / 2) {
      mPointF.x = kWidth;
      mPointF.y = kHeight;
    }
  }

  private void calculateAllPoints() {
    //A : the touch point
    //F : the right bottom corner
    //G
    mPointG.x = (mPointA.x + mPointF.x) / 2;
    mPointG.y = (mPointA.y + mPointF.y) / 2;
    //M
    mPointM.x = mPointG.x;
    mPointM.y = mPointF.y;
    //E
    float gm = mPointM.y - mPointG.y;
    float mf = mPointF.x - mPointM.x;
    float em = (gm * gm) / mf;
    mPointE.x = mPointM.x - em;
    mPointE.y = mPointF.y;
    //N
    mPointN.x = mPointF.x;
    mPointN.y = mPointG.y;
    //H
    float gn = mPointN.x - mPointG.x;
    float nf = mPointF.y - mPointN.y;
    float hn = (gn * gn) / nf;
    mPointH.x = mPointF.x;
    mPointH.y = mPointN.y - hn;

    //ag,bk cross p
    //assume ap = 0.5 * ag, then ap = gp = 0.5 * ag
    //then gp = (1- ratio(is 0.5)) *fp;
    //and ce = (1 - ratio) * fc;

    //C
    float ef = mPointF.x - mPointE.x;
    float ce = ef * (1 - mRightAngleRatio);
    mPointC.x = mPointE.x - ce;
    mPointC.y = mPointF.y;
    //J
    float hf = mPointF.y - mPointH.y;
    float jh = hf * (1 - mRightAngleRatio);
    mPointJ.x = mPointF.x;
    mPointJ.y = mPointH.y - jh;
    //B
    mPointB.x = mPointE.x + (mPointA.x - mPointE.x) * (1 - mRightAngleRatio);
    mPointB.y = mPointA.y + (mPointE.y - mPointA.y) * mRightAngleRatio;
    //K
    mPointK.x = mPointA.x + (mPointH.x - mPointA.x) * mRightAngleRatio;
    mPointK.y = mPointA.y + (mPointH.y - mPointA.y) * mRightAngleRatio;
    //D
    mPointBCMid.x = (mPointB.x + mPointC.x) / 2;
    mPointBCMid.y = (mPointB.y + mPointC.y) / 2;

    mPointD.x = (mPointBCMid.x + mPointE.x) / 2;
    mPointD.y = (mPointBCMid.y + mPointE.y) / 2;
    //I
    mPointJKMid.x = (mPointJ.x + mPointK.x) / 2;
    mPointJKMid.y = (mPointJ.y + mPointK.y) / 2;

    mPointI.x = (mPointJKMid.x + mPointH.x) / 2;
    mPointI.y = (mPointJKMid.y + mPointH.y) / 2;

    //mPointRotateCenter
    //mRotateDegree
    double aef = Math.atan2(kHeight - mPointA.y, mPointA.x - mPointE.x);
    mRotateDegree = 180 - (float) Math.toDegrees(aef);
    mPointRotateCenter.x = (float) (kWidth * Math.cos(Math.PI - aef)) + mPointA.x;
    mPointRotateCenter.y = (float) (kWidth * Math.sin(aef)) + mPointA.y;
  }

  private Path mPathNotCurrent = new Path();
  private Path mPathNext = new Path();

  private void makePath() {
    //make path of not current page
    mPathNotCurrent.reset();
    mPathNotCurrent.moveTo(mPointJ.x, mPointJ.y);
    mPathNotCurrent.quadTo(mPointH.x, mPointH.y, mPointK.x, mPointK.y);
    mPathNotCurrent.lineTo(mPointA.x, mPointA.y);
    mPathNotCurrent.lineTo(mPointB.x, mPointB.y);
    mPathNotCurrent.quadTo(mPointE.x, mPointE.y, mPointC.x, mPointC.y);
    mPathNotCurrent.lineTo(mPointF.x, mPointF.y);
    mPathNotCurrent.close();

    //make path of next page(2)
    mPathNext.reset();
    mPathNext.moveTo(mPointJ.x, mPointJ.y);
    mPathNext.quadTo(mPointI.x, mPointI.y, mPointI.x, mPointI.y);
    mPathNext.lineTo(mPointD.x, mPointD.y);
    mPathNext.quadTo(mPointC.x, mPointC.y, mPointC.x, mPointC.y);
    mPathNext.lineTo(mPointF.x, mPointF.y);
    mPathNext.close();
  }

  private void drawPoint(Canvas canvas, String text, PointF point) {
    canvas.drawCircle(point.x, point.y, 5, mPointPaint);
    canvas.drawText(" " + text, point.x, point.y, mPointPaint);
  }

  private void drawPoints(Canvas canvas) {
    drawPoint(canvas, "A", mPointA);
    drawPoint(canvas, "B", mPointB);
    drawPoint(canvas, "C", mPointC);
    drawPoint(canvas, "D", mPointD);
    drawPoint(canvas, "E", mPointE);
    drawPoint(canvas, "F", mPointF);
    drawPoint(canvas, "G", mPointG);
    drawPoint(canvas, "H", mPointH);
    drawPoint(canvas, "I", mPointI);
    drawPoint(canvas, "J", mPointJ);
    drawPoint(canvas, "K", mPointK);
    drawPoint(canvas, "M", mPointM);
    drawPoint(canvas, "N", mPointN);

    drawPoint(canvas, "BCMid", mPointBCMid);
    drawPoint(canvas, "JKMid", mPointJKMid);
    drawPoint(canvas, "X", mPointRotateCenter);
  }

  private void drawPage(Canvas canvas) {
    calculateAllPoints();
    makePath();

    //draw current page
    canvas.save();
    canvas.clipPath(mPathNotCurrent, Region.Op.XOR);
    canvas.drawBitmap(mCurPage, 0, 0, null);
    canvas.restore();
    //draw next page
    canvas.save();
    canvas.clipPath(mPathNotCurrent);
    canvas.clipPath(mPathNext, Region.Op.INTERSECT);
    canvas.drawBitmap(mNextPage, 0, 0, null);
    canvas.restore();
    //draw cur page back
    canvas.save();
    canvas.clipPath(mPathNotCurrent);
    canvas.clipPath(mPathNext, Region.Op.DIFFERENCE);
    mMatrix.reset();
    mMatrix.postTranslate(mPointRotateCenter.x - mPointF.x, mPointRotateCenter.y - mPointF.y);
    mMatrix.postRotate(mRotateDegree, mPointRotateCenter.x, mPointRotateCenter.y);
    canvas.drawBitmap(mCurPageBack, mMatrix, null);
    canvas.restore();

    drawPoints(canvas);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }
    if (mCurPageBack == null) {
      setup(getContext(), getWidth(), getHeight());
    }
    //  mMemCanvas.drawColor(Color.YELLOW);
    drawPage(canvas);
    //  canvas.drawBitmap(mBackground, 0, 0, null);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_MOVE) {
      //invalidate the canvas on move
      mPointA.x = event.getX();
      mPointA.y = event.getY();
      postInvalidate();
    }
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      //record the down point
      mPointA.x = event.getX();
      mPointA.y = event.getY();
      postInvalidate();
      JudgePointF();
    }
    if (event.getAction() == MotionEvent.ACTION_UP) {
      postInvalidate();
    }
    return true;
  }
}
