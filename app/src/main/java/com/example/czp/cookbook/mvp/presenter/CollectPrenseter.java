package com.example.czp.cookbook.mvp.presenter;

import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.view.CollectView;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public interface CollectPrenseter extends BasePrenseter<CollectView> {

    void queryData(String tableName, CollectDao dao);

    void delete(String tableName,CollectDao dao);

}
