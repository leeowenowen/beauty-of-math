package com.owo.bom.main;

import android.content.Context;

import com.owo.bom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangli on 16-10-20.
 */
public class MainData extends ArrayList<MainDataItem> {
    public MainData(Context context) {
        add(new MainDataItem(context.getResources().getString(R.string.main_title_bezier), //
                context.getResources().getString(R.string.main_desc_bezier),//
                "BezierDrawer"
        ));
    }
}
