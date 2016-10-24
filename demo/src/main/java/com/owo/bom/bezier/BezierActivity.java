package com.owo.bom.bezier;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.base.DataItem;
import com.owo.bom.base.GroupDataItem;
import com.owo.bom.bezier.details.SampleBezierActivity;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;
import java.util.List;

public class BezierActivity extends ListActivity {
    private static final String TAG = BezierActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BezierGroupAdapter adapter = new BezierGroupAdapter();
        // add basic group
        {
            List<DataItem> items = setupBasicListData();
            GroupDataItem group = new GroupDataItem();
            group.setTitle(getResources().getString(R.string.bezier_basic));
            group.setItems(items);
            adapter.addGroup(group);
        }
        // add advance group
        {
            List<DataItem> items = setupAdvanceListData();
            GroupDataItem group = new GroupDataItem();
            group.setTitle(getResources().getString(R.string.bezier_advance));
            group.setItems(items);
            adapter.addGroup(group);
        }
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        int type = getListAdapter().getItemViewType(position);
        if (BezierGroupAdapter.ITEM_VIEW_TYPE_TITLE == type) {
            return;
        }
        DataItem item = (DataItem) getListAdapter().getItem(position);
        Intent intent = new Intent(this, SampleBezierActivity.class);
        intent.putExtra(BezierConstants.KEY_TYPE, item.getId());
        startActivity(intent);
    }

    private List<DataItem> setupBasicListData() {
        List<DataItem> list = new ArrayList<>();
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL1, getResources().getString(R.string.bezier_level_1), //
                    getResources().getString(R.string.bezier_level_1),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL2, getResources().getString(R.string.bezier_level_2), //
                    getResources().getString(R.string.bezier_level_2),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL3, getResources().getString(R.string.bezier_level_3), //
                    getResources().getString(R.string.bezier_level_3),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVELN, getResources().getString(R.string.bezier_level_n), //
                    getResources().getString(R.string.bezier_level_n),//
                    sbv
            ));
        }
        return list;
    }

    private List<DataItem> setupAdvanceListData() {
        List<DataItem> list = new ArrayList<>();
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_ADVANCE_FLOWER, getResources().getString(R.string.bezier_pageturn), //
                    getResources().getString(R.string.bezier_pageturn),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_ADVANCE_WATERFLOW, getResources().getString(R.string.bezier_waterflow), //
                    getResources().getString(R.string.bezier_waterflow),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_ADVANCE_DRAG, getResources().getString(R.string.bezier_drag), //
                    getResources().getString(R.string.bezier_drag),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(this);
            list.add(new DataItem(BezierConstants.TYPE_ADVANCE_FLOWER, getResources().getString(R.string.bezier_flower), //
                    getResources().getString(R.string.bezier_flower),//
                    sbv
            ));
        }
        return list;
    }
}
