package com.example.czp.cookbook.mvp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzipeng on 2018/1/26.
 * function:
 */

public class CollectDao {

    private CollectHelper helper;
    private String id;

    public CollectDao(Context context) {
        this.helper = new CollectHelper(context);
    }

    public int insert(String tableName,CookDetailBean.ResultBean resultBean) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CollectTable.ID, resultBean.id);
        values.put(CollectTable.CLASS_ID, resultBean.classid);
        values.put(CollectTable.NAME, resultBean.name);
        values.put(CollectTable.PIC, resultBean.pic);
        int replace = (int) db.replace(tableName, null, values);
        db.close();
        return replace;
    }



    public int delete(String tableName) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int delete = db.delete(tableName, null, null);
        db.close();
        return delete;
    }

    public List<CookDetailBean.ResultBean> queryAll(String tableName) {
        List<CookDetailBean.ResultBean> lists = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from " + tableName;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            CookDetailBean.ResultBean resultBean = new CookDetailBean.ResultBean();
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String classid = cursor.getString(cursor.getColumnIndex(CollectTable.CLASS_ID));
            String name = cursor.getString(cursor.getColumnIndex(CollectTable.NAME));
            String pic = cursor.getString(cursor.getColumnIndex(CollectTable.PIC));

            resultBean.id = id;
            resultBean.name = name;
            resultBean.classid = classid;
            resultBean.pic = pic;
            lists.add(resultBean);
        }
        cursor.close();
        db.close();

        return lists;
    }

    public String queryInt(String cookName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select " + CollectTable.ID + " from " + CollectTable.TABLE_NAME
                + " where " + CollectTable.NAME + " = " + "'" + cookName + "'";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.move(1)) {
            id = cursor.getString(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();

        return id;
    }

}
