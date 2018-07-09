package com.example.czp.cookbook.mvp.presenter.impl;

import com.example.czp.cookbook.api.NetWork;
import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.mvp.model.bean.HttpMsg;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;
import com.example.czp.cookbook.mvp.presenter.SearchPrenseter;
import com.example.czp.cookbook.mvp.view.RefreshDataView;
import com.example.czp.cookbook.utils.Constan;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public class SreachPrenseterImpl extends BasePrenseterImpl<RefreshDataView> implements SearchPrenseter {

    public static SreachPrenseterImpl newInstance() {
        return new SreachPrenseterImpl();
    }


    @Override
    public void searchData(String name, int num) {
        rxManager.register(NetWork.createApi().getSearchData(name, num, Constan.APPKEY)
                .compose(RxManager.<HttpMsg<SearchBean>>rxSchedulerHelper())
                .subscribe(new Consumer<HttpMsg<SearchBean>>() {
                    @Override
                    public void accept(@NonNull HttpMsg<SearchBean> msg) throws Exception {
                        if(msg.result.msg.equals("ok")){
                            mView.getData(msg.result.result.list);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.errorToast(throwable.getMessage());
                    }
                }));

    }

    @Override
    public void loadMore(int classid,int start) {
        rxManager.register(NetWork.createApi().getSearchMore(classid, start, 10, Constan.APPKEY)
                .compose(RxManager.<HttpMsg<SearchBean>>rxSchedulerHelper())
                .subscribe(new Consumer<HttpMsg<SearchBean>>() {
                    @Override
                    public void accept(@NonNull HttpMsg<SearchBean> msg) throws Exception {
                        if(msg.result.msg.equals("ok")){
                            mView.loadMore(msg.result.result.list);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.loadMoreError(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void refreshData(String name, int num) {
        rxManager.register(NetWork.createApi().getSearchData(name, num, Constan.APPKEY)
                .compose(RxManager.<HttpMsg<SearchBean>>rxSchedulerHelper())
                .subscribe(new Consumer<HttpMsg<SearchBean>>() {
                    @Override
                    public void accept(@NonNull HttpMsg<SearchBean> msg) throws Exception {
                        if(msg.result.msg.equals("ok")){
                            mView.refreshData(msg.result.result.list);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.errorToast(throwable.getMessage());
                    }
                }));
    }


}
