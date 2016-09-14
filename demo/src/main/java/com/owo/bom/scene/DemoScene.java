package com.owo.bom.scene;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationSet;

/**
 * Created by wangli on 16-9-14.
 */
public class DemoScene extends View {
    public DemoScene(Context context) {
        super(context);
        setupAnimation();
        startAnimation();
    }

    private void setupAnimation() {
        AnimationSet as = new AnimationSet(false);
        as.addAnimation(null);


    }

    private void startAnimation() {

    }

}
