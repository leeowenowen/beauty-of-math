package com.owo.bom.main;

import android.content.Context;

import com.owo.base.util.UIUtil;
import com.owo.base.util.ViewUtil;
import com.owo.bom.R;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;
import java.util.List;

import owo.bom.SampleBezierView;

/**
 * Created by wangli on 16-10-20.
 */
public class MainData extends ArrayList<MainDataItem> {
    public MainData(Context context) {
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new MainDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new MainDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new MainDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
    }
}

