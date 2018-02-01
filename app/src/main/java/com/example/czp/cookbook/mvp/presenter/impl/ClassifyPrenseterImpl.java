package com.example.czp.cookbook.mvp.presenter.impl;

import com.example.czp.cookbook.api.NetWork;
import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;
import com.example.czp.cookbook.mvp.model.bean.HttpMsg;
import com.example.czp.cookbook.mvp.presenter.ClassifyPresenter;
import com.example.czp.cookbook.mvp.view.ResultDataView;
import com.example.czp.cookbook.utils.Constan;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class ClassifyPrenseterImpl extends BasePrenseterImpl<ResultDataView> implements ClassifyPresenter {

    public static ClassifyPrenseterImpl getInstance() {
        return new ClassifyPrenseterImpl();
    }


    @Override
    public void getClassifyNetWork() {

        rxManager.register(NetWork.createApi().getdata(Constan.APPKEY)
                .compose(RxManager.<HttpMsg<ClassifyBean>>rxSchedulerHelper())
                .subscribe(new Consumer<HttpMsg<ClassifyBean>>() {
                    @Override
                    public void accept(@NonNull HttpMsg<ClassifyBean> msg) throws Exception {
                        mView.getData(msg.result.result);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.errorToast(throwable.getMessage());
                    }
                }));
    }


}
