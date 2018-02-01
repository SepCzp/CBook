package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.view.View;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.adapter.BaseViewHolder;
import com.example.czp.cookbook.base.adapter.MyBaseAdapter;
import com.example.czp.cookbook.listener.OnItemClick;
import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class ClassifySortAdapter extends MyBaseAdapter<ClassifyBean.ResultBean.ListBean> {

    private OnItemClick listener;

    public ClassifySortAdapter(Context context, List<ClassifyBean.ResultBean.ListBean> data) {
        super(context, R.layout.recycle_classify_item, data);
    }

    @Override
    public void convert(BaseViewHolder holder, ClassifyBean.ResultBean.ListBean data, final int position) {
        holder.setText(R.id.tv_name, data.name);
        holder.setTextSize(R.id.tv_name, 15);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(v, position);
                }
            }
        });

    }

    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }
}
