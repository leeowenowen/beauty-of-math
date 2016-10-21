package com.owo.bom.main;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.bezier.BezierActivity;

import owo.bom.SampleBezierViewLevel1;

public class MainActivity extends ListActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        MainAdapter adapter = new MainAdapter(new MainData(this));
        setListAdapter(adapter);
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
//                view.invalidate();
//            }
//        });
//        director.start();
//        setContentView(view);
        //    setContentView(new SampleBezierViewLevel1(this));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                switchActivity(BezierActivity.class);
                break;
            default:
                break;
        }
    }

    private void switchActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

}
