package com.owo.bom.test;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;

import com.owo.bom.BuildConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 闪光灯控制类
 *
 * @author xiaoluo
 * @Date: 2015年6月4日下午2:08:37
 */
public class CameraController {
  private static final String TAG = "LZCameraManager";

  private static Camera sCamera;

  // 判断当前设备是否存在照相机Features，初始化时只赋值一次
  private static Boolean sHasFlash;

  // 当前是否已经开启了照相机的预览
  private static boolean sStartPreview;

  // 是否需要设置PreviewTexture
  private static Boolean sNeedSetPreviewTexture;

  private static Camera getCamera(Context context) {
    // 如果没闪光灯，返回null
    try {
      if (!hasFlash(context)) {
        if (BuildConfig.DEBUG) {
          Log.d(TAG, "your device has not flash!");
        }
        return null;
      }
      if (sCamera == null) {
        sCamera = Camera.open();
      }
      // 率先判断一下是否需要过滤PreviewTexture的设置
      needPreviewTexture();
      return sCamera;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 打开闪光灯
   */
  public static void openFlash(Context context) {
    try {
      if (getCamera(context) == null) {
        return;
      }
      Camera.Parameters parameters = sCamera.getParameters();
      parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
      sCamera.setParameters(parameters);
      // 如果已经开启了照相机的预览，则不需要重复再开启
      if (sStartPreview) {
        return;
      }

      if (needPreviewTexture() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        sCamera.setPreviewTexture(new SurfaceTexture(0));
      }
      sCamera.startPreview();

      sStartPreview = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 关闭闪光灯
   */
  public static void closeFlash(Context context) {
    try {
      if (sCamera == null) {
        return;
      }
      Camera.Parameters parameters = sCamera.getParameters();
      parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
      sCamera.setParameters(parameters);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 释放照相机资源
   */
  public static void release() {
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "release camera");
    }
    try {
      if (sCamera != null) {
        // 停止照相机的预览
        if (needPreviewTexture() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
          sCamera.setPreviewTexture(null);
        }
        sCamera.stopPreview();
        sCamera.release();
        sCamera = null;
      }
      sStartPreview = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 检测是否存在闪光灯,只在第一次时遍历当前设备的Features
   *
   * @return
   */
  private static boolean hasFlash(Context context) {
    if (sHasFlash == null) {
      sHasFlash = checkFlash(context);
    }
    return sHasFlash;
  }

  /**
   * 检测闪光灯Features
   *
   * @return
   */
  private static boolean checkFlash(Context context) {
    PackageManager pm = context.getPackageManager();
    if (pm == null) {
      return false;
    }
    return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
  }

  /**
   * 需要设置PreviewTexture机型集合
   */
  private static String[] NEED_SET_PREVIEW_MODELS = new String[] {"Nexus 5"};

  public static List<String> parseNeedSetPreivewModel() {
    List<String> modelList = new ArrayList<String>();
    modelList.addAll(Arrays.asList(NEED_SET_PREVIEW_MODELS));
    return modelList;
  }

  /**
   * 是否需要设置PreviewTexture
   *
   * @return
   */
  private static boolean needPreviewTexture() {
    if (sNeedSetPreviewTexture == null) {
      sNeedSetPreviewTexture = false;
      List<String> filterModels = parseNeedSetPreivewModel();
      for (int i = 0; i < filterModels.size(); i++) {
        String model = filterModels.get(i);
        // 判断机型是否需要设置PreviewTexture
        if (Build.MODEL.contains(model) || Build.MANUFACTURER.contains(model)) {
          if (BuildConfig.DEBUG) {
            Log.d(TAG, "your device need set preview texture");
          }
          sNeedSetPreviewTexture = true;
          break;
        }
      }
    }
    if (BuildConfig.DEBUG) {
      Log.d(TAG, "needPreviewTexture: " + sNeedSetPreviewTexture);
    }
    return sNeedSetPreviewTexture;
  }
}
