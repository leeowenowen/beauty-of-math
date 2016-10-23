package com.owo.bom.bezier;

import android.view.View;
import android.view.ViewGroup;

import com.owo.bom.base.CompositeAdapter;
import com.owo.bom.base.DataItem;
import com.owo.bom.base.GroupDataItem;
import com.owo.bom.base.ListDataAdapter;
import com.owo.bom.base.TitleDataAdapter;

import java.util.List;

/**
 * Created by wangli on 16-10-21.
 */
public class BezierGroupAdapter extends CompositeAdapter {
    public static final int ITEM_VIEW_TYPE_TITLE = 1;
    public static final int ITEM_VIEW_TYPE_ITEM = 2;

    public void addGroup(GroupDataItem group) {
        addChild(new TitleDataAdapter(group.getTitle(), ITEM_VIEW_TYPE_TITLE, false));
        addChild(new ListDataAdapter(group.getItems()).itemViewType(ITEM_VIEW_TYPE_ITEM).useConvertView(false));
    }
}
