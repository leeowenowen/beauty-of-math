package com.owo.bom.main;

import owo.bom.splash.Drawer;

/**
 * Created by wangli on 16-10-20.
 */
public class MainDataItem {
    private String title;
    private String description;
    private Drawer coverDrawer;

    public MainDataItem(String title, String description, Drawer coverDrawer) {
        this.title = title;
        this.description = description;
        this.coverDrawer = coverDrawer;
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

    public Drawer getCoverDrawer() {
        return coverDrawer;
    }

    public void setCoverDrawer(Drawer coverDrawer) {
        this.coverDrawer = coverDrawer;
    }
}
