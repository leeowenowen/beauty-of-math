package com.owo.bom.base;

import android.content.Context;

import com.owo.bom.R;
import com.owo.bom.main.covers.BezierCoverView;

import java.util.ArrayList;

/**
 * Created by wangli on 16-10-20.
 */
public class ListData extends ArrayList<ListDataItem> {
    public ListData(Context context) {
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new ListDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new ListDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
        {
            BezierCoverView sbv = new BezierCoverView(context);
            add(new ListDataItem(context.getResources().getString(R.string.main_title_bezier), //
                    context.getResources().getString(R.string.main_desc_bezier),//
                    sbv
            ));
        }
    }
}

