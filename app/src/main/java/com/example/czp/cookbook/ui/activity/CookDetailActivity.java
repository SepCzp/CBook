package com.example.czp.cookbook.ui.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.czp.cookbook.MyApplication;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseMvpActivity;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.presenter.impl.CookDetailImpl;
import com.example.czp.cookbook.mvp.view.ResultDataView;
import com.example.czp.cookbook.ui.view.FoldingTextView;
import com.example.czp.cookbook.utils.UIUtils;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class CookDetailActivity extends BaseMvpActivity<CookDetailImpl>
        implements ResultDataView<CookDetailBean> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_cook_icon)
    ImageView img_cook_icon;
    @BindView(R.id.tv_peoplenum)
    TextView tv_peoplenum;
    @BindView(R.id.tv_cookingtime)
    TextView tv_cookingtime;
    @BindView(R.id.nest_scroll)
    NestedScrollView nest_scroll;
    @BindView(R.id.fold_text)
    FoldingTextView foldingTextView;
    @BindView(R.id.tv_tag)
    TextView tv_tag;
    @BindView(R.id.ll_material)
    LinearLayout ll_material;
    @BindView(R.id.ll_practice)
    LinearLayout ll_practice;

    public CookDetailBean cookDetail;
    private CollectDao collectDao;

    @Override
    protected void initView() {
        super.initView();
        MyApplication.getRefWatcher().watch(this);
    }

    @Override
    protected void initData() {
        super.initData();
        collectDao = new CollectDao(this);

        Intent intent = getIntent();
        if (intent != null) {
            int id = Integer.parseInt(intent.getStringExtra("id"));
            mPresenter.getCookDetailData(id);
        }

    }

    @Override
    protected CookDetailImpl injectPrenseter() {
        return CookDetailImpl.getInstance();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_cook_detail1;


    }

    @Override
    public void getData(List<CookDetailBean> bean) {
        this.cookDetail = bean.get(0);
        mPresenter.browsingHistory(cookDetail,new CollectDao(CookDetailActivity.this));
        initToolBar(toolbar, cookDetail.result.name);
        tv_cookingtime.setText("烹煮时间：" + cookDetail.result.cookingtime);
        tv_peoplenum.setText("食用人数：" + cookDetail.result.peoplenum);
        foldingTextView.setText(cookDetail.result.content);
        tv_tag.setText("归类：" + cookDetail.result.tag);
        Glide.with(this).load(cookDetail.result.pic).into(img_cook_icon);

        for (CookDetailBean.ResultBean.MaterialBean m : cookDetail.result.material) {
            View view = UIUtils.inflate(R.layout.activity_detail_item);
            TextView tv_material = view.findViewById(R.id.tv_material);
            TextView tv_count = view.findViewById(R.id.tv_count);
            tv_material.setText(m.mname);
            tv_count.setText(m.amount);
            ll_material.addView(view);
        }

        for (int i = 0; i < cookDetail.result.process.size(); i++) {
            CookDetailBean.ResultBean.ProcessBean p = cookDetail.result.process.get(i);
            View view = UIUtils.inflate(R.layout.activity_detail_item2);
            TextView tv_practice = view.findViewById(R.id.tv_practice);
            ImageView img_practice = view.findViewById(R.id.img_practice);
            tv_practice.setText(i + 1 + "  " + p.pcontent);
            RequestOptions options = new RequestOptions();
            options.override(500,500);
            Glide.with(this).load(p.pic).apply(options).into(img_practice);
            ll_practice.addView(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.collect:
                mPresenter.addCollect(cookDetail, collectDao);
                break;
            case R.id.share:
                showToast("分享");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void errorToast(String error) {
        showToast(error);
    }
}
