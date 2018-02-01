package com.example.czp.cookbook.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.czp.cookbook.ui.view.SwitchFrameLayout;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public abstract class BaseFragment extends Fragment {

    private SwitchFrameLayout frameLayout;
    protected BaseActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        frameLayout = new SwitchFrameLayout(getActivity()) {
            @Override
            protected void NetWork() {
                netWork();
            }

            @Override
            protected View creatSucceedView() {
                return BaseFragment.this.creatSucceedView();
            }
        };

        return frameLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
        initData();
        mActivity = (BaseActivity) getActivity();
    }

    public void initData() {
    }

    protected abstract void netWork();

    protected abstract View creatSucceedView();

    public void setState(SwitchFrameLayout.Status state) {
        if (frameLayout != null) {
            frameLayout.setState(state);
        }
    }

    public void show() {
        if (frameLayout != null) {
            frameLayout.show();
        }
    }
}
