package com.example.czp.cookbook.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.czp.cookbook.R;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public class HotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public HotAdapter(@Nullable List<String> data) {
        super(R.layout.recycle_classify_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
        helper.addOnClickListener(R.id.tv_name);
    }


}
