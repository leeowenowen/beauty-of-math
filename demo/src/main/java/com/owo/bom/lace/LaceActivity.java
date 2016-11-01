package com.owo.bom.lace;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.base.DataItem;
import com.owo.bom.base.GroupDataItem;
import com.owo.bom.bezier.BezierGroupAdapter;
import com.owo.bom.lace.impl.SampleLaceActivity;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;
import java.util.List;

public class LaceActivity extends ListActivity {
  private static final String TAG = LaceActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    BezierGroupAdapter adapter = new BezierGroupAdapter();
    // add curve group
    {
      List<DataItem> items = setupCurveListData();
      GroupDataItem group = new GroupDataItem();
      group.setTitle(getResources().getString(R.string.lace_title));
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
    Intent intent = new Intent(this, SampleLaceActivity.class);
    intent.putExtra(LaceConstants.KEY_TYPE, item.getId());
    startActivity(intent);
  }

  private List<DataItem> setupCurveListData() {
    List<DataItem> list = new ArrayList<>();
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_LINEAR_FUNCTION,
                            getResources().getString(R.string.lace_linear_function),
                            //
                            getResources().getString(R.string.lace_linear_function),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_SINCOS,
                            getResources().getString(R.string.lace_sincos),
                            //
                            getResources().getString(R.string.lace_sincos),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_ARROW,
                            getResources().getString(R.string.lace_arrow),
                            //
                            getResources().getString(R.string.lace_arrow),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_CLOVER,
                            getResources().getString(R.string.lace_clover),
                            //
                            getResources().getString(R.string.lace_clover),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_INVOLUTE,
                            getResources().getString(R.string.lace_involute),
                            //
                            getResources().getString(R.string.lace_involute),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_GRASS,
                            getResources().getString(R.string.lace_grass),
                            //
                            getResources().getString(R.string.lace_grass),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_SPIRAL,
                            getResources().getString(R.string.lace_spiral),
                            //
                            getResources().getString(R.string.lace_spiral),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_STAR,
                            getResources().getString(R.string.lace_star),
                            //
                            getResources().getString(R.string.lace_star),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(LaceConstants.TYPE_CURVE_VERTICAL,
                            getResources().getString(R.string.lace_vertical),
                            //
                            getResources().getString(R.string.lace_vertical),
                            //
                            sbv));
    }
    return list;
  }

}
