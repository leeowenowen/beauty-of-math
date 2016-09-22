package owo.bom.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.Random;

/**
 * Created by wangli on 16-9-11.
 */
public class RandomUtil {
    private static final String TAG = "RandomUtil";

    private static int random() {
        return new Random(System.currentTimeMillis()).nextInt();
    }

    private static int randomX(Activity activity) {
        return random() % ScreenUtil.screenWidth(activity);
    }

    private static int randomY(Activity activity) {
        return random() % ScreenUtil.screenHeight(activity);
    }

    public static Point randomPoint(Activity activity) {
        int x = randomX(activity);
        int y = randomY(activity);
        x = Math.abs(x);
        x = Math.abs(y);
        Log.d(TAG, "" + x + ":" + y);
        return new Point(x, y);
    }

    public static int randomColorPart() {
        return random() % 255;
    }

    public static int randomColor() {
        return Color.argb(255, randomColorPart(), randomColorPart(), randomColorPart());
    }
}
