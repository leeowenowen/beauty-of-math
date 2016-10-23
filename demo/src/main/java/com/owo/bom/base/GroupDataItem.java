package com.owo.bom.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangli on 16-10-22.
 */
public class GroupDataItem {
    private String title;
    private List<DataItem> mItems = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<DataItem> getItems() {
        return mItems;
    }

    public void setItems(List<DataItem> items) {
        mItems = items;
    }

    public void addItem(DataItem item) {
        mItems.add(item);
    }
}
