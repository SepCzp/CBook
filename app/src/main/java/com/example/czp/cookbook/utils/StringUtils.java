package com.example.czp.cookbook.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by chenzipeng on 2018/6/4.
 * function:
 */
public class StringUtils {

    public static String isEmpty(@NonNull String s,@NonNull String defValue){
        if(TextUtils.isEmpty(s)){
            return defValue;
        }
        return s;
    }
}
