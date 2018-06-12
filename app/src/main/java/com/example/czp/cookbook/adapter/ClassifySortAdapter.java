package com.example.czp.cookbook.adapter;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.adapter.BaseAdapter;
import com.example.czp.cookbook.base.adapter.BaseViewHolder;
import com.example.czp.cookbook.mvp.model.bean.ClassifyBean;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class ClassifySortAdapter extends BaseAdapter<ClassifyBean.ResultBean.ListBean,BaseViewHolder> {


    public ClassifySortAdapter(List<ClassifyBean.ResultBean.ListBean> data) {
        super(R.layout.recycle_classify_item, data);
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_name, data.get(position).name);
        holder.setTextSize(R.id.tv_name, 15);

    }
}
