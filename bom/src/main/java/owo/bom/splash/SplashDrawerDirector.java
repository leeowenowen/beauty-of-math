package owo.bom.splash;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.Arrays;

import owo.bom.drawer.AnyBezierDrawer;
import owo.bom.drawer.Flower2Drawer;
import owo.bom.drawer.FlowerDrawer;
import owo.bom.drawer.WaterDrawer;

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
//        addSection(new AnimatedDrawerSection(new FlowerDrawer(center)) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofPropertyValuesHolder(drawer,
//                        PropertyValuesHolder.ofFloat("rOut", 100, 600),
//                        PropertyValuesHolder.ofInt("n", 2, 100),
//                        PropertyValuesHolder.ofFloat("rIn", 50, 300)
//                )
//                        .setDuration(10000);
//            }
//        });
        AnyBezierDrawer anyBezierDrawer = new AnyBezierDrawer();
        anyBezierDrawer.setControlPoints(Arrays.asList(new Point(0, 0),
                        new Point(200, 50),
                        new Point(0, 100),
                        new Point(200, 150),
                        new Point(0, 200),
                        new Point(200, 250),
                        new Point(0, 300),
                        new Point(200, 350),
                        new Point(0, 400),
                        new Point(200, 450),
                        new Point(0, 500)
                ),
                Arrays.asList(Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA), 200
        );
        anyBezierDrawer.setControlPoints(Arrays.asList(new Point(0, 0),
                        new Point(300, 300),
                        new Point(0, 300),
                        new Point(0, 600),
                        new Point(300, 300),
                        new Point(300, 600),
                        new Point(600, 600),
                        new Point(300, 300),
                        new Point(600, 300),
                        new Point(600, 0),
                        new Point(300, 300),
                        new Point(300, 0),
                        new Point(0, 0)
                ),
                Arrays.asList(Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.RED), 200
        );
//        addSection(new AnimatedDrawerSection(anyBezierDrawer) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofPropertyValuesHolder(drawer,
//                        PropertyValuesHolder.ofInt("i", 0, 200)
//                )
//                        .setDuration(20000);
//            }
//        });
//        addSection(new AnimatedDrawerSection(new Flower2Drawer(center)) {
//            @Override
//            protected ValueAnimator createAnimator(Drawer drawer) {
//                return ObjectAnimator.ofPropertyValuesHolder(drawer,
//                        PropertyValuesHolder.ofFloat("rOut", 100, 600),
//                        PropertyValuesHolder.ofInt("n", 2, 100),
//                        PropertyValuesHolder.ofFloat("rIn", 0, 0)
//                )
//                        .setDuration(10000);
//            }
//        });

        addSection(new AnimatedDrawerSection(new WaterDrawer()) {
            @Override
            protected ValueAnimator createAnimator(Drawer drawer) {
                return ObjectAnimator.ofPropertyValuesHolder(drawer,
                        PropertyValuesHolder.ofInt("xOffset", 0, 600)
                )
                        .setDuration(10000);
            }
        });
    }
}
