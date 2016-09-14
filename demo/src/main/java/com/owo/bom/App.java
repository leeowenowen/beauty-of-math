package com.owo.bom;

import android.app.Application;

import owo.bom.UIHandler;

/**
 * Created by wangli on 16-9-14.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UIHandler.init();
    }
}
