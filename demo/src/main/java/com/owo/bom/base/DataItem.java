package com.owo.bom.base;

import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangli on 16-10-21.
 */
public class DataItem {
    private String id;
    private String title;
    private String description;
    private View cover;

    public DataItem(String title, String description, View cover) {
        this(null, title, description, cover);
    }

    public DataItem(String id, String title, String description, View cover) {
        this.id = id;
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

    private Map<String, String> mData = new HashMap<>();

    public void set(String key, String value) {
        mData.put(key, value);
    }

    public void get(String key) {
        mData.get(key);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
