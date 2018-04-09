package com.example.czp.cookbook.mvp.presenter.impl;

import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.mvp.presenter.BasePrenseter;
import com.example.czp.cookbook.mvp.view.BaseView;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class BasePrenseterImpl<V extends BaseView>
        implements BasePrenseter<V> {

    protected V mView;
    protected RxManager rxManager = new RxManager();

    @Override
    public void attchView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
            rxManager.unSubscribe();
        }

    }

}
