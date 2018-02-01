package com.example.czp.cookbook.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.BoutiqueAdapter;
import com.example.czp.cookbook.adapter.HotAdapter;
import com.example.czp.cookbook.adapter.MyPageAdpater;
import com.example.czp.cookbook.base.ui.BaseFragment;
import com.example.czp.cookbook.mvp.model.bean.BoutiqueBean;
import com.example.czp.cookbook.ui.activity.CookDetailActivity;
import com.example.czp.cookbook.ui.activity.SearchActivity;
import com.example.czp.cookbook.ui.activity.SearchRuselfActivity;
import com.example.czp.cookbook.ui.view.IndicatorView;
import com.example.czp.cookbook.ui.view.SwitchFrameLayout;
import com.example.czp.cookbook.utils.Constan;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class CookBookFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    public static final int GO = 1;//
    public static final int TIME = 3000;//
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.indicator)
    IndicatorView indicator;
    @BindView(R.id.recycle_hot)
    RecyclerView recycle_hot;
    @BindView(R.id.recycle_good)
    RecyclerView recycle_good;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private MyHandler handler;
    private boolean b = false;
    private MyPageAdpater pageAdpater;

    @Override
    protected void netWork() {
        setState(SwitchFrameLayout.Status.success);
    }

    @Override
    protected View creatSucceedView() {
        View view = View.inflate(getContext(), R.layout.fragment_cookbook1, null);
        ButterKnife.bind(this, view);

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.goActivity(SearchActivity.class);
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int state) {
                if (state == -appBarLayout.getTotalScrollRange()) {
                    tv_title.setVisibility(View.VISIBLE);
                } else {
                    tv_title.setVisibility(View.GONE);
                }
            }
        });


        viewPager.addOnPageChangeListener(this);

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        pageAdpater = new MyPageAdpater(getContext());
        viewPager.setAdapter(pageAdpater);

        handler = new MyHandler(viewPager);
        handler.sendEmptyMessageDelayed(GO, TIME);
        viewPager.setCurrentItem(500);
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);

            }
        });


        List<String> list = new ArrayList<>();
        for (int i = 0; i < Constan.HOT.length; i++) {
            list.add(Constan.HOT[i]);
        }
        HotAdapter adapter = new HotAdapter(list);
        recycle_hot.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recycle_hot.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String classid = Constan.CLASS_ID[position];
                Intent intent = new Intent();
                intent.putExtra("classid", classid);
                intent.putExtra("name", adapter.getData().get(position).toString());
                mActivity.goActivityData(intent, SearchRuselfActivity.class);
            }
        });

        List<BoutiqueBean> data = new ArrayList<>();
        for (int i = 0; i < Constan.NAME_ICON.length; i++) {
            BoutiqueBean boutiqueBean = new BoutiqueBean();
            boutiqueBean.name = Constan.NAME[i];
            boutiqueBean.icon = Constan.NAME_ICON[i];
            boutiqueBean.classid = Constan.ID[i];
            data.add(boutiqueBean);
        }

        BoutiqueAdapter boutiqueAdapter = new BoutiqueAdapter(getContext(), data);
        recycle_good.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycle_good.setAdapter(boutiqueAdapter);
        boutiqueAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BoutiqueBean bean = (BoutiqueBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra("id", bean.classid);
                mActivity.goActivityData(intent, CookDetailActivity.class);
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indicator.setSelect(position % Constan.ICON.length);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                handler.removeMessages(GO);
                b = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:

                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                if (b) {
                    handler.sendEmptyMessageDelayed(GO, TIME);
                    b = false;
                }

                break;
        }
    }

    private static class MyHandler extends Handler {

        WeakReference<ViewPager> w;

        public MyHandler(ViewPager w) {
            this.w = new WeakReference<ViewPager>(w);
        }

        @Override
        public void handleMessage(Message msg) {
            if (w == null) {
                return;
            }
            switch (msg.what) {
                case GO:
                    ViewPager viewPager = w.get();
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    sendEmptyMessageDelayed(GO, TIME);
                    break;
            }

            super.handleMessage(msg);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(GO);
    }
}
