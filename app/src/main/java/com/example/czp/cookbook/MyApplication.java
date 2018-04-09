package com.example.czp.cookbook;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int mainThreadid;
    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        mainThreadid = Process.myTid();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainThreadid() {
        return mainThreadid;
    }
}
