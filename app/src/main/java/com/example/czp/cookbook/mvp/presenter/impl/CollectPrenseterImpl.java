package com.example.czp.cookbook.mvp.presenter.impl;

import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.model.dao.CollectTable;
import com.example.czp.cookbook.mvp.presenter.CollectPrenseter;
import com.example.czp.cookbook.mvp.view.CollectView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public class CollectPrenseterImpl extends BasePrenseterImpl<CollectView> implements CollectPrenseter {

    public static CollectPrenseterImpl getInstance() {
        return new CollectPrenseterImpl();
    }


    @Override
    public void queryData(String tableName, CollectDao dao) {
        Observable.just(dao.queryAll(tableName))
                .compose(RxManager.<List<CookDetailBean.ResultBean>>rxSchedulerHelper())
                .subscribe(new Consumer<List<CookDetailBean.ResultBean>>() {
                    @Override
                    public void accept(@NonNull List<CookDetailBean.ResultBean> resultBeen) throws Exception {
                        mView.queryData(resultBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.queryError(throwable.getMessage());
                    }
                });
    }

    @Override
    public void delete(String tableName, CollectDao dao) {
        Observable.just(dao.delete(CollectTable.TABLE_NAME))
                .compose(RxManager.<Integer>rxSchedulerHelper())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mView.delete(integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.deleteError(throwable.getMessage());
                    }
                });
    }
}
