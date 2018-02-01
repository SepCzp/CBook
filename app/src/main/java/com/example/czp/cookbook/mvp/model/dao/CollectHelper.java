package com.example.czp.cookbook.mvp.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenzipeng on 2018/1/26.
 * function:
 */

public class CollectHelper extends SQLiteOpenHelper {
    public CollectHelper(Context context) {
        super(context, "demo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CollectTable.CREATTABLE);
        db.execSQL(BrowseTable.CREAtE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
