package com.example.czp.cookbook.mvp.view;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public interface CollectView<T> extends BaseView {

    void queryData(T data);

    void delete(int i);

    void queryError(String error);

    void deleteError(String error);

}
