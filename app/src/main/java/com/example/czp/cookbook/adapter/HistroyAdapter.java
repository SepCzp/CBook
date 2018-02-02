package com.example.czp.cookbook.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.czp.cookbook.R;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/31.
 * function:
 */

public class HistroyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HistroyAdapter(){
        super(R.layout.recycle_histroy_item);
    }

    public HistroyAdapter(@Nullable List<String> data) {
        super(R.layout.recycle_histroy_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_head, item);
        helper.setTextColor(R.id.tv_head, Color.BLACK);
        helper.addOnClickListener(R.id.tv_head);
    }
}
