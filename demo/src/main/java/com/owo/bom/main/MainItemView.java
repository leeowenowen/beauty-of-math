package com.owo.bom.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owo.bom.R;

import org.w3c.dom.Text;

/**
 * Created by wangli on 16-10-20.
 */
public class MainItemView extends LinearLayout {
    private ImageView mCover;
    private TextView mTitle;
    private TextView mDescription;

    public MainItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.grid_item, this, true);
        mCover = (ImageView) findViewById(R.id.cover);
        mTitle = (TextView) findViewById(R.id.title);
        mDescription = (TextView) findViewById(R.id.desc);
    }

    public void update(MainDataItem item) {
        mTitle.setText(item.getTitle());
        mDescription.setText(item.getDescription());
    }
}
