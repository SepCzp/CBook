package com.example.czp.cookbook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by chenzipeng on 2018/1/31.
 * function:
 */

public class SpUtils {

    public static volatile SpUtils spUtils = null;
    public static SharedPreferences sp = null;//
    public static final String NAME = "history";//

    private SpUtils() {
    }

    public static SpUtils getInstance() {
        if (spUtils == null) {
            sp = UIUtils.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
            spUtils = new SpUtils();
        }
        return spUtils;
    }

    public static void save(String key, Object values) {
        if (values instanceof String) {
            sp.edit().putString(key, (String) values).apply();
        } else if (values instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) values).apply();
        } else if (values instanceof Integer) {
            sp.edit().putInt(key, (Integer) values).apply();
        }
    }

    public static <T> void saveList(List<T> list, String key) {
        String histroy = new Gson().toJson(list);
        save(key, histroy);
    }

    /**
     * 获取List
     *
     * @param key sp key值
     * @param <T> item 类型
     * @return list
     */
    public static <T> List<T> getDataList(String key, Class<T> cls) {
        List<T> datalist = new ArrayList<T>();
        String strJson = getString(key);
        if (null == strJson) {
            return datalist;
        }

        try {
            Gson gson = new Gson();
            //datalist = gson.fromJson(strJson, new TypeToken<List<T>>(){}.getType());
            JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
            for (final JsonElement elem : array) {
                datalist.add(gson.fromJson(elem, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datalist;
    }

    public static String getString(String key) {
        return sp.getString(key, null);
    }

    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static Set<String> getSet(String key) {
        return sp.getStringSet(key, null);
    }

    public static void clear() {
        sp.edit().clear().apply();
    }

    public static void remove(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        sp.edit().remove(key).apply();
    }

}
