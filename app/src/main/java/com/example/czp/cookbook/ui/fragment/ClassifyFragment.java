package com.example.czp.cookbook.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.ClassifyMenuAdapter;
import com.example.czp.cookbook.adapter.ClassifySortAdapter;
import com.example.czp.cookbook.base.adapter.BaseAdapter;
import com.example.czp.cookbook.base.ui.BaseMvpFragment;
import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;
import com.example.czp.cookbook.mvp.presenter.impl.ClassifyPrenseterImpl;
import com.example.czp.cookbook.mvp.view.ResultDataView;
import com.example.czp.cookbook.ui.activity.SearchResultActivity;
import com.example.czp.cookbook.ui.view.SwitchFrameLayout;
import com.example.czp.cookbook.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class ClassifyFragment extends BaseMvpFragment<ClassifyPrenseterImpl>
        implements ResultDataView<ClassifyBean.ResultBean> {

    @BindView(R.id.rv_mainMenu)
    RecyclerView rv_mainMenu;
    @BindView(R.id.rv_sort)
    RecyclerView rv_sort;

    private List<ClassifyBean.ResultBean> bean;
    private ClassifySortAdapter sortAdapter;
    private ClassifyMenuAdapter classifyMenuAdapter;

    @Override
    protected void netWork() {
        mPrenseter.getClassifyNetWork();
    }

    @Override
    protected View creatSucceedView() {
        View view = UIUtils.inflate(getActivity(),R.layout.fragment_classify_data);
        ButterKnife.bind(this, view);

        classifyMenuAdapter = new ClassifyMenuAdapter(bean);
        classifyMenuAdapter.closeLoadMoreFeatures();
        rv_mainMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_mainMenu.setAdapter(classifyMenuAdapter);

        classifyMenuAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View v,int i) {
                sortAdapter.setData(bean.get(i).list);
                classifyMenuAdapter.setBackgroundColor(i);
            }
        });



        sortAdapter = new ClassifySortAdapter(bean.get(0).list);
        sortAdapter.closeLoadMoreFeatures();
        rv_sort.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_sort.setAdapter(sortAdapter);
        sortAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View v,int i) {
                List<ClassifyBean.ResultBean.ListBean> data = sortAdapter.getData();
                ClassifyBean.ResultBean.ListBean listBean = data.get(i);
                Intent intent = new Intent();
                intent.putExtra("name", listBean.name);
                intent.putExtra("classid", listBean.classid);
                mActivity.goActivityData(intent,SearchResultActivity.class);
            }
        });


        return view;
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    protected ClassifyPrenseterImpl injectPrenseter() {
        return ClassifyPrenseterImpl.getInstance();
    }

    @Override
    public void getData(List<ClassifyBean.ResultBean> bean) {
        this.bean = bean;
        setState(SwitchFrameLayout.Status.success);
    }

    @Override
    public void errorToast(String error) {
        showToast(error);
        setState(SwitchFrameLayout.Status.erroe);
    }
}
