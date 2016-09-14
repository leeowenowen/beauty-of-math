package owo.bom;


import android.os.Handler;

public class UIHandler {

    private static Handler sHandler;

    public static void init() {
        sHandler = new Handler();
    }

    public static void post(Runnable r) {
        sHandler.post(r);
    }

    public static void postDelayed(Runnable r, long delay) {
        sHandler.postDelayed(r, delay);
    }
}
