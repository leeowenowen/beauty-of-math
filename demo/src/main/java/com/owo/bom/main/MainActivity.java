package com.owo.bom.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.base.DataItem;
import com.owo.bom.base.ListDataAdapter;
import com.owo.bom.bezier.BezierActivity;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
  private static final String TAG = MainActivity.class.getSimpleName();
  private ListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mListView = new ListView(this);
    ListDataAdapter adapter = new ListDataAdapter(setupListData());
    mListView.setAdapter(adapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
          case 0:
            switchActivity(BezierActivity.class);
            break;
          default:
            break;
        }
      }
    });
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

    setContentView(mListView);
  }

  private List<DataItem> setupListData() {
    List<DataItem> list = new ArrayList<>();
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(getResources().getString(R.string.main_title_bezier), //
                            getResources().getString(R.string.main_desc_bezier),//
                            sbv));
    }
    return list;
  }

  private void switchActivity(Class cls) {
    startActivity(new Intent(this, cls));
  }

}
