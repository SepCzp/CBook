package com.example.czp.cookbook.adapter;

import android.graphics.Color;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.adapter.BaseAdapter;
import com.example.czp.cookbook.base.adapter.BaseViewHolder;
import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class ClassifyMenuAdapter extends BaseAdapter<ClassifyBean.ResultBean,BaseViewHolder> {

    public int mSelect = 0;

    public ClassifyMenuAdapter(List<ClassifyBean.ResultBean> data) {
        super(R.layout.recycle_classify_item, data);
    }

    public void setBackgroundColor(int position) {
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_name, data.get(position).name);

        if (mSelect == position) {
            holder.setTextColor(R.id.tv_name, Color.parseColor("#fa4301"));
            holder.itemView.setBackgroundColor(Color.WHITE);
        } else {
            holder.setTextColor(R.id.tv_name, Color.BLACK);
            holder.itemView.setBackgroundColor(Color.parseColor("#cacaca"));
        }
    }
}
