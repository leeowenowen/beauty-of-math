package com.owo.bom.test;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wangli on 10/27/16.
 */

public class TestView extends LinearLayout {

  public TestView(Context context) {
    super(context);
    setOrientation(LinearLayout.VERTICAL);
    TextView tv = new TextView(context);
    tv.setText("tv________");
    addView(tv);
    {
      Button btn = new Button(context);
      btn.setText("btn");
      btn.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          ViewHelper.setRotationX(getChildAt(0), 50);
        }
      });
      addView(btn);
    }
    {
      Button btnOpen = new Button(context);
      btnOpen.setText("open");
      btnOpen.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          CameraController.openFlash(getContext());
        }
      });
      addView(btnOpen);
    }
    {
      Button btnClose = new Button(context);
      btnClose.setText("close");
      btnClose.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          CameraController.closeFlash(getContext());
        }
      });
      addView(btnClose);
    }

  }


}
