package com.owo.bom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.CloverView;
import owo.bom.HeartView;
import owo.bom.InvoluteView;
import owo.bom.SpiralView;
import owo.bom.axis.ArrowView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrowView view = new ArrowView(this);
        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
