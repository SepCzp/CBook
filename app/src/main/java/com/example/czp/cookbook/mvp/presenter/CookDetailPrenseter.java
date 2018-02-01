package com.example.czp.cookbook.mvp.presenter;

import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.view.ResultDataView;

/**
 * Created by chenzipeng on 2018/1/22.
 * function:
 */

public interface CookDetailPrenseter extends BasePrenseter<ResultDataView> {
    void getCookDetailData(int id);

    void addCollect(CookDetailBean bean, CollectDao dao);

    void browsingHistory(CookDetailBean bean, CollectDao dao);
}
