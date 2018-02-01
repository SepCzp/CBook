package com.example.czp.cookbook.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public abstract class MyBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> data;
    private int layoutId;
    public Context context;


    public MyBaseAdapter(Context context, @LayoutRes int layoutId, List<T> data) {
        this.context = context;
        this.data = data == null ? new ArrayList<T>() : data;
        this.layoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.creatHolderView(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, data.get(position), position);
    }



    public abstract void convert(BaseViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return data.size() > 0 ? data.size() : 0;
    }


    public void refresh(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }
}
