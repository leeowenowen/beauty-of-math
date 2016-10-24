package com.owo.bom.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.owo.bom.R;

/**
 * Created by wangli on 16-10-24.
 */
public class DisplayActivity extends Activity {
    protected FrameLayout mDisplayContainer;
    protected TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mDisplayContainer = (FrameLayout) findViewById(R.id.display_parent);
        mDescription = (TextView) findViewById(R.id.description);
    }
}
