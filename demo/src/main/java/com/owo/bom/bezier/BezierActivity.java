package com.owo.bom.bezier;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.owo.bom.R;

public class BezierActivity extends ListActivity {
    private static final String TAG = BezierActivity.class.getSimpleName();
    private ListView mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

}
