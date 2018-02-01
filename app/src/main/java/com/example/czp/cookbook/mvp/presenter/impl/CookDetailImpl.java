package com.example.czp.cookbook.mvp.presenter.impl;

import com.example.czp.cookbook.api.NetWork;
import com.example.czp.cookbook.api.RxManager;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.bean.HttpMsg;
import com.example.czp.cookbook.mvp.model.dao.BrowseTable;
import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.model.dao.CollectTable;
import com.example.czp.cookbook.mvp.presenter.CookDetailPrenseter;
import com.example.czp.cookbook.mvp.view.ResultDataView;
import com.example.czp.cookbook.utils.Constan;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenzipeng on 2018/1/22.
 * function:
 */

public class CookDetailImpl extends BasePrenseterImpl<ResultDataView> implements CookDetailPrenseter {

    public static CookDetailImpl getInstance() {
        return new CookDetailImpl();
    }

    @Override
    public void getCookDetailData(int id) {
        rxManager.register(NetWork.createApi().getCookDetailData(id, Constan.APPKEY)
                .compose(RxManager.<HttpMsg<CookDetailBean>>rxSchedulerHelper())
                .subscribe(new Consumer<HttpMsg<CookDetailBean>>() {
                    @Override
                    public void accept(@NonNull HttpMsg<CookDetailBean> cookDetailBeanHttpMsg) throws Exception {
                        CookDetailBean result = cookDetailBeanHttpMsg.result;
                        List<CookDetailBean> cookDetailBeen = new ArrayList<CookDetailBean>();
                        cookDetailBeen.add(result);
                        mView.getData(cookDetailBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.errorToast(throwable.getMessage());
                    }
                }));
    }

    @Override
    public void addCollect(CookDetailBean bean, final CollectDao dao) {
        Observable.just(bean)
                .observeOn(Schedulers.io())
                .map(new Function<CookDetailBean, Integer>() {
                    @Override
                    public Integer apply(@NonNull CookDetailBean cookDetailBean) throws Exception {
                        return dao.insert(CollectTable.TABLE_NAME, cookDetailBean.result);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        if (integer > 0) {
                            mView.showToast("收藏成功");
                        } else mView.showToast("收藏失败");
                    }
                });
    }

    @Override
    public void browsingHistory(final CookDetailBean bean, final CollectDao dao) {
        Observable.just(bean)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<CookDetailBean>() {
                    @Override
                    public void accept(@NonNull CookDetailBean cookDetailBean) throws Exception {
                        dao.insert(BrowseTable.TABLE_NAME, bean.result);
                    }
                });
    }
}
