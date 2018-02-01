package com.example.czp.cookbook.mvp.model.dao;

/**
 * Created by chenzipeng on 2018/1/26.
 * function:
 */

public class CollectTable {
    public static final String CLASS_ID = "classid";//
    public static final String NAME = "name";//
    public static final String PIC = "pic";//
    public static final String ID = "id";//
    public static final String TABLE_NAME = "collect";//
    public static final String CREATTABLE = "create table if not exists "
            + TABLE_NAME + " ("
            + ID + " varchar(10) primary key,"
            + CLASS_ID + " text ,"
            + NAME + " varchar(100),"
            + PIC + " varchar(100));";//
}
