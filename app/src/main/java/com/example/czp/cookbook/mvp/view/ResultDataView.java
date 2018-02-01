package com.example.czp.cookbook.mvp.view;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public interface ResultDataView<T> extends BaseView {

    void getData(List<T> bean);

    void errorToast(String error);
}
