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
import com.owo.bom.base.*;
import com.owo.bom.base.ListDataItem;
import com.owo.bom.bezier.BezierActivity;
import com.owo.bom.main.covers.BezierCoverView;


import java.util.ArrayList;
import java.util.List;

import owo.bom.SampleBezierViewLevel1;

public class MainActivity extends ListActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListDataAdapter adapter = new ListDataAdapter(setupListData());
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

    private List<ListDataItem> setupListData() {
        List<ListDataItem> list = new ArrayList<>();
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new ListDataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new ListDataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new ListDataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        return list;
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
