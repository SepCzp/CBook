package com.example.czp.cookbook.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.CollectAdapter;
import com.example.czp.cookbook.base.ui.BaseMvpActivity;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;
import com.example.czp.cookbook.mvp.model.dao.BrowseTable;
import com.example.czp.cookbook.mvp.model.dao.CollectDao;
import com.example.czp.cookbook.mvp.model.dao.CollectTable;
import com.example.czp.cookbook.mvp.presenter.impl.CollectPrenseterImpl;
import com.example.czp.cookbook.mvp.view.CollectView;

import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BaseMvpActivity<CollectPrenseterImpl> implements CollectView<List<CookDetailBean.ResultBean>> {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle)
    RecyclerView recyclerView;
    private CollectAdapter adapter;
    private CollectDao dao;
    private int count;
    private Intent intent;
    private String type;

    @Override
    protected void initData() {
        super.initData();
        dao = new CollectDao(this);
        if (type.equals("collect")) {
            mPresenter.queryData(CollectTable.TABLE_NAME, dao);
        } else {
            mPresenter.queryData(BrowseTable.TABLE_NAME, dao);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        setStatus();
        intent = getIntent();
        type = intent.getStringExtra("type");
        initToolBar(toolbar, null);
        tv_title.setText(type.equals("collect") ? "收藏" : "浏览记录");
    }

    @Override
    protected CollectPrenseterImpl injectPrenseter() {
        return CollectPrenseterImpl.getInstance();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_collect;
    }


    @Override
    public void queryData(List<CookDetailBean.ResultBean> data) {
        adapter = new CollectAdapter(CollectActivity.this, data, type.equals("collect"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CollectActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<CookDetailBean.ResultBean> data = adapter.getData();
                String id = data.get(position).id;
                Intent intent = new Intent();
                intent.putExtra("id", id);
                goActivityData(intent, CookDetailActivity.class);
            }
        });
    }

    @Override
    public void delete(int i) {
        if (i > 0) {
            showToast("删除成功");
        } else {
            showToast("删除失败");
        }
    }

    @Override
    public void queryError(String error) {
        showToast("查询失败" + error);
    }

    @Override
    public void deleteError(String error) {
        showToast("删除失败" + error);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ment_collect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear) {
            if (adapter.getData().size() == 0) {
                showToast("没有数据可操作");
            }

            if (type.equals("collect")) {
                mPresenter.delete(CollectTable.TABLE_NAME, dao);
                adapter.getData().clear();
                adapter.notifyDataSetChanged();

            } else {
                mPresenter.delete(BrowseTable.TABLE_NAME, dao);
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
