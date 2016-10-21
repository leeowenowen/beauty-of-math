package com.owo.bom.bezier;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.base.DataItem;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;
import java.util.List;

public class BezierActivity extends ListActivity {
    private static final String TAG = BezierActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    private List<DataItem> setupListData() {
        List<DataItem> list = new ArrayList<>();
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(getResources().getString(R.string.main_title_bezier), //
                    getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        return list;
    }
}
