package com.owo.bom;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.owo.base.util.UIHandler;

import io.fabric.sdk.android.Fabric;

/**
 * Created by wangli on 16-9-14.
 */
public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    sContext = this;

    UIHandler.init();
    Fabric.with(this, new Crashlytics());
  }

  private static Context sContext;

  public static Context context() {
    return sContext;
  }
}
