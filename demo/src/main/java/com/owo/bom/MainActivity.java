package com.owo.bom;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import owo.bom.ArrowView;
import owo.bom.AxisView;
import owo.bom.Bezier;
import owo.bom.BezierView;
import owo.bom.Y_ax_b_View;
import owo.bom.axis.Axis;
import owo.bom.sincos_View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   Debug.waitForDebugger();
        Bezier view = new Bezier(this);
        Point[] pts = new Point[]{
                new Point(50, 20),
                new Point(10, 180),
                new Point(200, 200),
                new Point(250, 30),
                new Point(300, 200),
        };
        view.setControlPoints(pts);

        view.setBackgroundColor(Color.GREEN);
        setContentView(view);
    }
}
