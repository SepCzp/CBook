package com.example.czp.cookbook.mvp.view;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public interface RefreshDataView<T> extends ResultDataView<T> {

    void refreshData();

    void loadMore(List<T> data);

    void loadMoreError(String error);
}
