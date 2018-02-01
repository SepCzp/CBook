package com.example.czp.cookbook.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class ActivityManager {

    private static Stack<Activity> stack;
    private static ActivityManager activityManager;

    public static ActivityManager getInstance() {
        if (activityManager == null) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    /**
     * 添加activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (stack == null) {
            stack = new Stack<>();
        }
        stack.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (stack.contains(activity)) {
            stack.remove(activity);
        }
    }

    /**
     * 结束activity
     *
     * @param activity
     */
    public void killActivity(Activity activity) {
        if (activity != null && stack.contains(activity)) {
            stack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束栈顶activity
     */
    public void killTopActivity() {
        Activity activity = stack.lastElement();
        killActivity(activity);
    }


    /**
     * 结束所有Activity
     */
    private void killAllActivity() {
        for (int i = 0, size = stack.size(); i < size; i++) {
            if (null != stack.get(i)) {
                stack.get(i).finish();
            }
        }
        stack.clear();
    }
}
