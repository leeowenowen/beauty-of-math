package owo.bom.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangli on 9/19/16.
 */
public class DrawDirector implements Drawer {
  private List<Section> sectionList = new ArrayList<>();
  private int curIndex;
  private ValueAnimator.AnimatorUpdateListener listener;

  public void setAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
    this.listener = listener;
  }

  public void addSection(final Section section) {
    sectionList.add(section);
    section.animator().addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        curIndex++;
        startCurSection();
      }
    });
    section.animator().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        Log.d("xxx", "onAnimationUpdate");
        if (listener != null) {
          listener.onAnimationUpdate(animation);
        }
      }
    });
  }

  public void start() {
    curIndex = 0;
    startCurSection();
  }

  private void startCurSection() {
    if (curIndex == sectionList.size()) {
      // no more drawer,notify end
    } else {
      startAnimator();
    }
  }

  private void startAnimator() {
    sectionList.get(curIndex).animator().start();
  }

  @Override
  public void draw(Canvas canvas) {
    if (sectionList.isEmpty() || curIndex >= sectionList.size()) {
      return;
    }
    sectionList.get(curIndex).drawer().draw(canvas);
  }
}
