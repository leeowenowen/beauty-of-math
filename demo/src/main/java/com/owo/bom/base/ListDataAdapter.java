package com.owo.bom.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wangli on 16-10-20.
 */
public class ListDataAdapter extends BaseAdapter {
    private List<DataItem> mData;
    private int mItemViewType;

    public ListDataAdapter itemViewType(int itemViewType) {
        mItemViewType = itemViewType;
        return this;
    }

    public ListDataAdapter(List<DataItem> mainData) {
        mData = mainData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView view = null;
        if (convertView == null) {
            view = new ItemView(parent.getContext());
        } else {
            view = (ItemView) convertView;
        }
        view.update(mData.get(position));
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemViewType;
    }
}
