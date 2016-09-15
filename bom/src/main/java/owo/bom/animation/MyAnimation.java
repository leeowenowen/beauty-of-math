package owo.bom.animation;


import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyAnimation extends Animation {
    private static final String TAG = "MyAnimation";

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        float alpha = t.getAlpha();
        int type = t.getTransformationType();
        Log.d(TAG, "" + interpolatedTime + ":" + alpha + ":" + type + ":" + matrix.toString());

    }
}

