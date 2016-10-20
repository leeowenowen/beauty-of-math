package com.owo.bom.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owo.base.util.ViewUtil;
import com.owo.bom.R;
import com.owo.bom.main.covers.BezierCoverView;

import org.w3c.dom.Text;

import owo.bom.SampleBezierView;

/**
 * Created by wangli on 16-10-20.
 */
public class MainItemView extends LinearLayout {
    private FrameLayout mCover;
    private TextView mTitle;
    private TextView mDescription;

    public MainItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.grid_item, this, true);
        mCover = (FrameLayout) findViewById(R.id.cover);
        mTitle = (TextView) findViewById(R.id.title);
        mDescription = (TextView) findViewById(R.id.desc);
        setBackgroundColor(Color.RED);
    }

    public void update(MainDataItem item) {
        mTitle.setText(item.getTitle());
        mDescription.setText(item.getDescription());
        mCover.removeAllViews();
        //  ViewUtil.clearParent(item.getCover());
        mCover.addView(new BezierCoverView(item.getCover().getContext()), new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }
}
