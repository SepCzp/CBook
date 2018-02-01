package com.example.czp.cookbook.mvp.presenter;

import com.example.czp.cookbook.mvp.model.BaseModel;
import com.example.czp.cookbook.mvp.view.BaseView;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public interface BasePrenseter<V extends BaseView> {

    void attchView(V view);

    void detachView();

}
