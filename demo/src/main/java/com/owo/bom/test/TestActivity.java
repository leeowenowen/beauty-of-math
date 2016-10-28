package com.owo.bom.test;

import android.app.Activity;
import android.os.Bundle;

import owo.bom.WaterDropView;

public class TestActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(new WaterDropView(this));
  }
}
