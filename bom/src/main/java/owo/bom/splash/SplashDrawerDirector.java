package owo.bom.splash;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.PointF;

import owo.bom.drawer.FlowerDrawer;

/**
 * Created by wangli on 9/19/16.
 */
public class SplashDrawerDirector extends DrawDirector {
    private PointF center;

    public SplashDrawerDirector(PointF center) {
        this.center = center;
        setupSections();
    }

    private void setupSections() {
//        addSection(new AnimatedDrawerSection(new PointToCircleDrawer(center)) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofFloat(drawer, "radius", 0, 100).setDuration(2000);
//            }
//        });
//        addSection(new AnimatedDrawerSection(new CircleToCrossDrawer(center)) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofPropertyValuesHolder(drawer,
//                        PropertyValuesHolder.ofFloat("startRadius",
//                                100,
//                                1),
//                        PropertyValuesHolder.ofFloat("endRadius",
//                                50,
//                                1),
//                        PropertyValuesHolder.ofFloat(
//                                "circleCenterDistance",
//                                0,
//                                500)).setDuration(10000);
//            }
//        });
//        CrossToAxisDrawer crossToAxisDrawer = new CrossToAxisDrawer(center);
//        crossToAxisDrawer.setArrowOffset(450);
//        crossToAxisDrawer.setArrowLength(50);
//        addSection(new AnimatedDrawerSection(crossToAxisDrawer) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofPropertyValuesHolder(drawer,
//                        PropertyValuesHolder.ofFloat("angle",
//                                (float) Math.PI,
//                                (float) Math.PI /
//                                        6))
//                        .setDuration(5000);
//            }
//        });
        addSection(new AnimatedDrawerSection(new FlowerDrawer(center)) {
            @Override
            protected ValueAnimator createAnimator(Drawer drawer) {
                return ObjectAnimator.ofPropertyValuesHolder(drawer,
                        PropertyValuesHolder.ofFloat("rOut", 100, 600),
                        PropertyValuesHolder.ofInt("n", 2, 100),
                        PropertyValuesHolder.ofFloat("rIn", 50, 300)
                )
                        .setDuration(10000);
            }
        });
    }
}
