package com.owo.bom;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.ArrowView;
import owo.bom.AxisView;
import owo.bom.Y_ax_b_View;
import owo.bom.axis.Axis;
import owo.bom.sincos_View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sincos_View view = new sincos_View(this);
        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
