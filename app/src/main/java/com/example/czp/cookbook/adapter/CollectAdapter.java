package com.example.czp.cookbook.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseActivity;
import com.example.czp.cookbook.mvp.model.bean.CookDetailBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/26.
 * function:
 */

public class CollectAdapter extends BaseQuickAdapter<CookDetailBean.ResultBean, BaseViewHolder> {

    private BaseActivity context;
    private boolean b;

    public CollectAdapter(BaseActivity context, @Nullable List<CookDetailBean.ResultBean> data, boolean b) {
        super(R.layout.collect_item, data);
        this.context = context;
        this.b = b;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CookDetailBean.ResultBean item) {
        Glide.with(context).load(item.pic).into((ImageView) helper.getView(R.id.img_collect_icon));
        helper.setText(R.id.tv_collect_name, item.name);
        helper.addOnClickListener(R.id.card_collect);
        helper.setVisible(R.id.img_collect, b);
    }
}
