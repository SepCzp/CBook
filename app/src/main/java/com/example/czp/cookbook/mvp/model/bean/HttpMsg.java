package com.example.czp.cookbook.mvp.model.bean;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class HttpMsg<T> {

    public String code;
    public boolean charge;
    public String msg;
    public T result;

}
