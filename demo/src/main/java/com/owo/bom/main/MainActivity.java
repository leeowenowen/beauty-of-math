package com.owo.bom.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;

import com.owo.bom.R;

import owo.bom.SampleBezierViewLevel1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        //-------------------------------------------------
        setContentView(R.layout.activity_main);

        mMainView = (ListView) findViewById(R.id.listView);
        MainAdapter adapter = new MainAdapter(new MainData(this));
        mMainView.setAdapter(adapter);
//        final View view = new View(this);
//        PointF center = new PointF();
//        DisplayMetrics dm = WindowUtil.getScreenSize(this);
//        center.x = dm.widthPixels / 2;
//        center.y = dm.heightPixels / 2;
//        final SplashDrawerDirector director = new SplashDrawerDirector(center);
//        UIUtil.setBackgroundDrawable(view, new BaseDrawable() {
//            @Override
//            public void draw(Canvas canvas) {
//                director.draw(canvas);
//            }
//        });
//        director.setAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                view.invalidate();
//            }
//        });
//        director.start();
//        setContentView(view);
    //    setContentView(new SampleBezierViewLevel1(this));
    }

}
