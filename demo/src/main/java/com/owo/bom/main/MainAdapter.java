package com.owo.bom.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by wangli on 16-10-20.
 */
public class MainAdapter extends BaseAdapter {
    private MainData mMainData;

    public MainAdapter(MainData mainData) {
        mMainData = mainData;
    }

    @Override
    public int getCount() {
        return mMainData == null ? 0 : mMainData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainItemView view = null;
        if (convertView == null) {
            view = new MainItemView(parent.getContext());
        } else {
            view = (MainItemView) convertView;
        }
        view.update(mMainData.get(position));
        return view;
    }
}
