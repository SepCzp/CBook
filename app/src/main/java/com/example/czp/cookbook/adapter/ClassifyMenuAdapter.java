package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class ClassifyMenuAdapter extends MyBaseAdapter<ClassifyBean.ResultBean> {

    public int mSelect = 0;
    private OnItemClick listener;

    public ClassifyMenuAdapter(Context context, List<ClassifyBean.ResultBean> data) {
        super(context, R.layout.recycle_classify_item, data);
    }

    @Override
    public void convert(final BaseViewHolder holder, final ClassifyBean.ResultBean data, final int position) {
        holder.setText(R.id.tv_name, data.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroudColor(position);
                if (listener != null) {
                    listener.itemClick(v, position);
                }
            }
        });

        if (mSelect == position) {
            holder.setTextColor(R.id.tv_name, Color.parseColor("#fa4301"));
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            holder.setTextColor(R.id.tv_name, Color.BLACK);
            holder.itemView.setBackgroundColor(Color.parseColor("#cacaca"));
        }
    }

    public void setBackgroudColor(int position) {
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }
}
