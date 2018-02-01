package com.example.czp.cookbook.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.SearchAdapter;
import com.example.czp.cookbook.base.ui.BaseMvpActivity;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;
import com.example.czp.cookbook.mvp.presenter.impl.SreachPrenseterImpl;
import com.example.czp.cookbook.mvp.view.RefreshDataView;

import java.util.List;

import butterknife.BindView;

public class SearchRuselfActivity extends BaseMvpActivity<SreachPrenseterImpl>
        implements RefreshDataView<SearchBean.ResultBean.ListBean>, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_result)
    RecyclerView rv_result;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;
//    @BindView(R.id.img_back)
//    ImageView img_back;
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
//        img_back.setVisibility(View.VISIBLE);
//        ed_search.setVisibility(View.VISIBLE);
//        tv_search.setVisibility(View.VISIBLE);

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
        if(TextUtils.isEmpty(classid)){
            mPresenter.searchData(name, 20);
        }else {
            mPresenter.searchData(name,count);
        }

    }

    @Override
    protected SreachPrenseterImpl injectPrenseter() {
        return SreachPrenseterImpl.newInstance();
    }


    @Override
    public void getData(List<SearchBean.ResultBean.ListBean> bean) {
        adapter = new SearchAdapter(this, bean);
        rv_result.setLayoutManager(new LinearLayoutManager(this));
        rv_result.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchBean.ResultBean.ListBean b = (SearchBean.ResultBean.ListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("id", b.id + "");
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(SearchRuselfActivity.this
                        , view.findViewById(R.id.img_greens), "nice");
                goActivityData(intent, CookDetailActivity.class, compat.toBundle());
            }
        });
        if (!isSearch && !TextUtils.isEmpty(classid)) {
            adapter.setOnLoadMoreListener(this, rv_result);
        }

    }

    @Override
    public void errorToast(String error) {
        showToast(error);
    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData() {

    }

    /**
     * 加载
     *
     * @param data
     */
    @Override
    public void loadMore(List<SearchBean.ResultBean.ListBean> data) {
        if (data.size() == 0) {
            return;
        } else {
            adapter.addData(data);
        }
        adapter.loadMoreComplete();

    }

    /**
     * 加载更多失败
     *
     * @param error
     */
    @Override
    public void loadMoreError(String error) {
        showToast(error);
        adapter.loadMoreComplete();
    }


    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore(Integer.parseInt(classid), start);
        start += 10;
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


}
