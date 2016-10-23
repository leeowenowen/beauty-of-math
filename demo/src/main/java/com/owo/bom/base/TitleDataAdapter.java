package com.owo.bom.base;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.owo.bom.R;

import org.w3c.dom.Text;

/**
 * Created by wangli on 16-10-23.
 */
public class TitleDataAdapter extends BaseAdapter {
    private final String mTitle;
    private final int mItemViewType;
    private boolean mUseConvertView = true;

    public TitleDataAdapter(String title, int itemViewType) {
        this(title, itemViewType, true);
    }

    public TitleDataAdapter(String title, int itemViewType, boolean useConvertView) {
        mTitle = title;
        mItemViewType = itemViewType;
        mUseConvertView = useConvertView;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return mTitle;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemViewType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = null;
        if (mUseConvertView && convertView != null) {
            view = new TextView(parent.getContext());
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getContext().getResources().getDimensionPixelOffset(R.dimen.group_title_height));
            view.setLayoutParams(lp);
            view.setBackgroundColor(Color.BLUE);
        } else {
            view = (TextView) convertView;
        }
        view.setText(mTitle);
        return view;
    }
}
