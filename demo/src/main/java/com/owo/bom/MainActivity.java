package com.owo.bom;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import owo.bom.Bezier;
import owo.bom.BezierView;
import owo.bom.MatrixView;
import owo.bom.SampleBezierView;
import owo.bom.SampleBezierViewLevel1;
import owo.bom.WaterDropView;
import owo.bom.animation.MyAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   Debug.waitForDebugger();
//        Bezier view = new Bezier(this);
//        Point[] pts = new Point[]{
////                new Point(100, 150), new Point(80, 100), new Point(50, 50), new Point(100, 0),
////                new Point(150, 50), new Point(120, 100), new Point(100, 150),
//                new Point(0, 0), new Point(100, 150), new Point(300, 150), new Point(450, 0),
//        };
        //  view.setControlPoints(pts);
        //  SampleBezierView view = new SampleBezierView(this);

        // view.setBackgroundColor(Color.BLACK);
        MatrixView view = new MatrixView(this);
        setContentView(view);
        MyAnimation myAnimation = new MyAnimation();
        myAnimation.setDuration(1000);
        myAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(myAnimation);


    }
}
