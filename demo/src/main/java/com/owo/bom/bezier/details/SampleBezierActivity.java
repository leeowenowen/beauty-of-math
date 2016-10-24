package com.owo.bom.bezier.details;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.owo.bom.R;
import com.owo.bom.base.DisplayActivity;
import com.owo.bom.bezier.BezierConstants;

/**
 * Created by wangli on 16-10-24.
 */
public class SampleBezierActivity extends DisplayActivity {
    protected FrameLayout mDisplayContainer;
    protected TextView mDescription;
    private BezierSampleViewFactory mBezierSampleViewFactory = new BezierSampleViewFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mDisplayContainer = (FrameLayout) findViewById(R.id.display_parent);
        mDescription = (TextView) findViewById(R.id.description);
        String type = getIntent().getStringExtra(BezierConstants.KEY_TYPE);
        View sampleView = mBezierSampleViewFactory.create(this, type);
        mDisplayContainer.addView(sampleView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }
}
