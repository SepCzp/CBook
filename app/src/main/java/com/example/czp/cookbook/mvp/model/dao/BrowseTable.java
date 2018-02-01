package com.example.czp.cookbook.mvp.model.dao;

/**
 * Created by chenzipeng on 2018/1/29.
 * function:
 */

public class BrowseTable {

    public static final String TABLE_NAME = "browse";//
    public static final String ID = "id";//
    public static final String PIC = "pic";//
    public static final String NAME = "name";//
    public static final String CLASSID = "classid";//
    public static final String CREAtE_TABLE = "create table if not exists "
            + TABLE_NAME + " ("
            + ID + " varchar(10) primary key, "
            + NAME + " text, "
            + CLASSID + " text,"
            + PIC + " text)";//
}
