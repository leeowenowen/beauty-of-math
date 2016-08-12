package com.owo.bom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.CloverView;
import owo.bom.HeartView;
import owo.bom.SpiralView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpiralView view = new SpiralView(this);
        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
