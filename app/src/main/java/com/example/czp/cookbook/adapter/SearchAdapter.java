package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.adapter.BaseAdapter;
import com.example.czp.cookbook.mvp.model.bean.SearchBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/19.
 * function:
 */

public class SearchAdapter extends BaseAdapter<SearchBean.ResultBean.ListBean, com.example.czp.cookbook.base.adapter.BaseViewHolder> {
    private Context context;

    public SearchAdapter(Context context) {
        super(R.layout.recycle_result_item);
        this.context = context;
    }

    public SearchAdapter(Context context, List<SearchBean.ResultBean.ListBean> data) {
        super(R.layout.recycle_result_item, data);
        this.context = context;
    }


    @Override
    public void convert(com.example.czp.cookbook.base.adapter.BaseViewHolder holder, int position) {
        SearchBean.ResultBean.ListBean item = data.get(position);
        Glide.with(context).load(item.pic)
                .into((ImageView) holder.getView(R.id.img_greens));
        holder.setText(R.id.tv_greens_name, item.name);
        holder.setText(R.id.tv_content, item.content);
        holder.setText(R.id.tv_tag, item.tag);

    }
}
