package com.owo.bom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.CloverView;
import owo.bom.HeartView;
import owo.bom.InvoluteView;
import owo.bom.SpiralView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InvoluteView view = new InvoluteView(this);
        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
