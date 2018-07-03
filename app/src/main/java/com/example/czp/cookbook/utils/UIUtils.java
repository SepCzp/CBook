package com.example.czp.cookbook.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.czp.cookbook.MyApplication;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class UIUtils {

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getScreenHeight(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }


    /**
     * 获取上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    public static boolean isMainThread() {
        return Process.myTid() == MyApplication.getMainThreadid();
    }

    public static void runUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void post(Runnable runnable) {
        getHandler().post(runnable);
    }

    public static View inflate(@LayoutRes int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }
    public static View inflate(Context context,@LayoutRes int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }
}
