package com.example.czp.cookbook.mvp.presenter;

import com.example.czp.cookbook.mvp.view.RefreshDataView;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public interface SearchPrenseter extends BasePrenseter<RefreshDataView> {

    void searchData(String name, int num);


    void loadMore(int classid, int start);
}
