package com.example.czp.cookbook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by chenzipeng on 2018/3/8.
 * function: bitmapUtils
 */

public class BitmapUtils {


    /**
     * 比例压缩图片
     *
     * @param fileName 文件名
     * @param pW       宽度
     * @param pH       高度
     * @return
     */
    public static Bitmap compressImage(String fileName, int pW, int pH) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //只读取图片属性
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //预加载
        BitmapFactory.decodeFile(fileName, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        options.inSampleSize = getInSampleSize(outWidth, outHeight, pW, pH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(fileName, options);
    }

    private static int getInSampleSize(int outWidth, int outHeight, int pW, int pH) {
        int scale = 2;
        if (outWidth > pW || outHeight > pH) {
            int width = outWidth / 2;
            int height = outHeight / 2;
            while ((width / scale) >= pW && (height / scale >= pH)) {
                scale *= 2;
            }
        }

        return 2;
    }
}
