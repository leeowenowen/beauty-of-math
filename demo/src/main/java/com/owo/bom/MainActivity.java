package com.owo.bom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.CloverView;
import owo.bom.HeartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CloverView view = new CloverView(this);
        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
