package com.owo.bom.main;

import android.view.View;

import owo.bom.splash.Drawer;

/**
 * Created by wangli on 16-10-20.
 */
public class MainDataItem {
    private String title;
    private String description;
    private View cover;

    public MainDataItem(String title, String description, View cover) {
        this.title = title;
        this.description = description;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public View getCover() {
        return cover;
    }

    public void setCover(View cover) {
        this.cover = cover;
    }
}
