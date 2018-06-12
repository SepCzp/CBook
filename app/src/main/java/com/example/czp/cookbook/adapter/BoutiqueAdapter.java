package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.mvp.model.bean.BoutiqueBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public class BoutiqueAdapter extends BaseQuickAdapter<BoutiqueBean, BaseViewHolder> {

    private Context context;

    public BoutiqueAdapter(Context context, @Nullable List<BoutiqueBean> data) {
        super(R.layout.collect_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BoutiqueBean item) {
        Glide.with(context).load(item.icon).into((ImageView) helper.getView(R.id.img_collect_icon));
        helper.setText(R.id.tv_collect_name, item.name);
        helper.addOnClickListener(R.id.card_collect);
        helper.setVisible(R.id.img_collect,false);
    }
}
