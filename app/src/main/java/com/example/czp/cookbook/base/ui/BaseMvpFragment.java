package com.example.czp.cookbook.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.czp.cookbook.mvp.presenter.BasePrenseter;
import com.example.czp.cookbook.mvp.view.BaseView;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public abstract class BaseMvpFragment<P extends BasePrenseter> extends BaseFragment implements BaseView {

    protected P mPrenseter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrenseter = injectPrenseter();
        mPrenseter.attchView(this);
    }

    protected abstract P injectPrenseter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPrenseter != null) {
            mPrenseter.detachView();
        }
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
