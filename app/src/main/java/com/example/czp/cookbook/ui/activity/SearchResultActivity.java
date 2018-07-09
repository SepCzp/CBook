package com.example.czp.cookbook.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.czp.cookbook.MyApplication;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.SearchAdapter;
import com.example.czp.cookbook.base.adapter.BRecyclerView;
import com.example.czp.cookbook.base.adapter.BaseAdapter;
import com.example.czp.cookbook.base.ui.BaseMvpActivity;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;
import com.example.czp.cookbook.mvp.presenter.impl.SreachPrenseterImpl;
import com.example.czp.cookbook.mvp.view.RefreshDataView;

import java.util.List;

import butterknife.BindView;

public class SearchResultActivity extends BaseMvpActivity<SreachPrenseterImpl>
        implements RefreshDataView<SearchBean.ResultBean.ListBean>,BaseAdapter.LoadMoreListener, BRecyclerView.onRefreshListener {

    @BindView(R.id.rv_result)
    BRecyclerView rv_result;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_empty)
    RelativeLayout rl_empty;
//    @BindView(R.id.ed_search)
//    EditText ed_search;
//    @BindView(R.id.tv_search)
//    TextView tv_search;

    private List<SearchBean.ResultBean.ListBean> searchBeen;
    private int count = 10;
    private String name;
    private SearchAdapter adapter;
    private String classid;
    private int start = 10;
    private SearchView searchView;
    private SearchView.SearchAutoComplete mEdit;
    private boolean isSearch = false;

    @Override
    protected int layoutResID() {
        return R.layout.activity_seach_ruself;
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setContentInsetStartWithNavigation(0);
        setStatus();
        tv_title.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            classid = intent.getStringExtra("classid");
        }
        initToolBar(toolbar, name);
        if (TextUtils.isEmpty(classid)) {
            mPresenter.searchData(name, 10);
        } else {
            mPresenter.searchData(name, count);
        }

        adapter = new SearchAdapter(this);
        adapter.setLoadMoreListener(this);

        rv_result.setLayoutManager(new LinearLayoutManager(this))
                .setAdapter(adapter)
                .setIsAllowedRefresh(true)
                .setOnRefreshListener(this);

        adapter.setOnItemClickListener((view,i)->{
            SearchBean.ResultBean.ListBean b = adapter.getData().get(i);
            Intent intent1 = new Intent();
            intent.putExtra("id", b.id + "");
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(SearchResultActivity.this
                    , view.findViewById(R.id.img_greens), "nice");
            goActivityData(intent1, CookDetailActivity.class, compat.toBundle());
        });

    }

    @Override
    protected SreachPrenseterImpl injectPrenseter() {
        return SreachPrenseterImpl.newInstance();
    }


    @Override
    public void getData(List<SearchBean.ResultBean.ListBean> bean) {
        rl_empty.setVisibility(View.GONE);
        adapter.addData(bean);
        if (!isSearch && !TextUtils.isEmpty(classid) || bean.size() >=10) {
            adapter.openLoadMore();
        }else {
            adapter.loadMoreEnd();
        }

    }

    @Override
    public void errorToast(String error) {
        adapter.loadMoreComplete();
        showToast(error);
        rl_empty.setVisibility(View.VISIBLE);
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData(List<SearchBean.ResultBean.ListBean> data) {
        showToast("刷新成功");
        rl_empty.setVisibility(View.GONE);
        adapter.setData(data);
        if (!isSearch && !TextUtils.isEmpty(classid) || data.size() >=10) {
            adapter.openLoadMore();
        }else {
            adapter.loadMoreEnd();
        }
        start=10;
    }

    /**
     * 加载
     *
     * @param data
     */
    @Override
    public void loadMore(List<SearchBean.ResultBean.ListBean> data) {
        if (data.size() == 0) {
            adapter.loadMoreEnd();
            return;
        }
        adapter.addData(data);
        //adapter.loadMoreComplete();

    }

    /**
     * 加载更多失败
     *
     * @param error
     */
    @Override
    public void loadMoreError(String error) {
        showToast(error);
        adapter.loadMoreFail();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore(Integer.parseInt(classid), start);
        start += 10;
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData(name, 10);
        rv_result.setRefresh(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        mEdit = searchView.findViewById(R.id.search_src_text);
        mEdit.setTextColor(Color.WHITE);
        searchView.setQueryHint("今天想吃什么菜???");
        mEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {

                    String trim = v.getText().toString().trim();
                    if (!TextUtils.isEmpty(trim)) {
                        mPresenter.searchData(trim, 20);
                        isSearch = true;
                    } else {
                        mPresenter.searchData(name, 10);
                        isSearch = false;
                    }
                }
                return false;
            }
        });

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher().watch(this);

    }


}
