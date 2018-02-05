package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;
import com.example.czp.cookbook.utils.GlideRoundTransform;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public class SearchAdapter extends BaseQuickAdapter<SearchBean.ResultBean.ListBean, BaseViewHolder> {
    private Context context;

    public SearchAdapter(Context context) {
        super(R.layout.recycle_result_item);
        this.context = context;
        openLoadAnimation();
    }

    public SearchAdapter(Context context, List<SearchBean.ResultBean.ListBean> data) {
        super(R.layout.recycle_result_item, data);
        this.context = context;
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean.ResultBean.ListBean item) {
        Glide.with(context).load(item.pic)
                .transform(new GlideRoundTransform(context, 4))
                .into((ImageView) helper.getView(R.id.img_greens));
        helper.setText(R.id.tv_greens_name, item.name);
        helper.setText(R.id.tv_content, item.content);
        helper.setText(R.id.tv_tag, item.tag);

        helper.addOnClickListener(R.id.cardview);
    }


}
