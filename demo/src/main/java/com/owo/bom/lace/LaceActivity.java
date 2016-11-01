package com.owo.bom.lace;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.owo.bom.R;
import com.owo.bom.base.DataItem;
import com.owo.bom.base.GroupDataItem;
import com.owo.bom.bezier.BezierConstants;
import com.owo.bom.bezier.BezierGroupAdapter;
import com.owo.bom.bezier.impl.SampleBezierActivity;
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
      List<DataItem> items = setupBasicListData();
      GroupDataItem group = new GroupDataItem();
      group.setTitle(getResources().getString(R.string.curve_title));
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
      list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL1,
                            getResources().getString(R.string.bezier_level_1),
                            //
                            getResources().getString(R.string.bezier_level_1),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL2,
                            getResources().getString(R.string.bezier_level_2),
                            //
                            getResources().getString(R.string.bezier_level_2),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVEL3,
                            getResources().getString(R.string.bezier_level_3),
                            //
                            getResources().getString(R.string.bezier_level_3),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_BASIC_LEVELN,
                            getResources().getString(R.string.bezier_level_n),
                            //
                            getResources().getString(R.string.bezier_level_n),
                            //
                            sbv));
    }
    return list;
  }

  private List<DataItem> setupAdvanceListData() {
    List<DataItem> list = new ArrayList<>();
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_ADVANCE_PAGETURN,
                            getResources().getString(R.string.bezier_pageturn),
                            //
                            getResources().getString(R.string.bezier_pageturn),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_ADVANCE_WATERFLOW,
                            getResources().getString(R.string.bezier_waterflow),
                            //
                            getResources().getString(R.string.bezier_waterflow),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_ADVANCE_DRAG,
                            getResources().getString(R.string.bezier_drag),
                            //
                            getResources().getString(R.string.bezier_drag),
                            //
                            sbv));
    }
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_ADVANCE_FLOWER,
                            getResources().getString(R.string.bezier_flower),
                            //
                            getResources().getString(R.string.bezier_flower),
                            //
                            sbv));
    }
    return list;
  }


  private List<DataItem> setupPrincipleListData() {
    List<DataItem> list = new ArrayList<>();
    {
      BezierCoverView sbv = new BezierCoverView(this);
      list.add(new DataItem(BezierConstants.TYPE_PRINCIPLE,
                            getResources().getString(R.string.bezier_principle),
                            //
                            getResources().getString(R.string.bezier_principle),
                            //
                            sbv));
    }
    return list;
  }
}
