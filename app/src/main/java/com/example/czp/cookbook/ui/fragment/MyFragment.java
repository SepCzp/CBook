package com.example.czp.cookbook.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseActivity;
import com.example.czp.cookbook.base.ui.BaseFragment;
import com.example.czp.cookbook.ui.activity.CollectActivity;
import com.example.czp.cookbook.ui.view.SwitchFrameLayout;
import com.example.czp.cookbook.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.browse)
    TextView tv_browse;

    @Override
    protected void netWork() {
        setState(SwitchFrameLayout.Status.success);
    }

    @Override
    protected View creatSucceedView() {
        View view = UIUtils.inflate(R.layout.fragment_my);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_collect)
    public void collectUi() {
        Intent intent = new Intent();
        intent.putExtra("type", "collect");
        ((BaseActivity) (getActivity())).goActivityData(intent, CollectActivity.class);
    }

    @OnClick(R.id.browse)
    public void browse(){
        Intent intent = new Intent();
        intent.putExtra("type", "browse");
        ((BaseActivity) (getActivity())).goActivityData(intent, CollectActivity.class);
    }
}
