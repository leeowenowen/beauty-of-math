package com.owo.bom.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangli on 16-10-21.
 */
public abstract class CompositeAdapter extends BaseAdapter {
    private List<BaseAdapter> mChildren = new ArrayList<>();

    public void addChild(BaseAdapter child) {
        mChildren.add(child);
    }

    public void removeChild(BaseAdapter child) {
        mChildren.remove(child);
    }

    @Override
    public int getCount() {
        int count = 0;
        for (BaseAdapter baseAdapter : mChildren) {
            count += baseAdapter.getCount();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        int count = 0;
        int preCount = 0;
        for (BaseAdapter baseAdapter : mChildren) {
            preCount = count;
            count += baseAdapter.getCount();
            if (position >= preCount && position < count) {
                int curpos = position - preCount;
                return baseAdapter.getItem(curpos);
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    protected void beforeAddAdapter(BaseAdapter adapter) {
    }

    protected void aterAddAdapter(BaseAdapter adapter) {

    }

    protected void beforeRemoveAdapter(BaseAdapter adapter) {
    }

    protected void afterRemoveAdapter(BaseAdapter adapter) {
    }

}
