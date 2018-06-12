package com.example.czp.cookbook.base.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chenzipeng on 2018/6/8.
 * function:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArrayCompat<View> views;//装载控件
    public View itemView;
    private BaseAdapter adapter;

    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArrayCompat<>();
        this.itemView = itemView;
    }

    public  <V extends View> V getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = this.itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    public static BaseViewHolder createViewHolder(@LayoutRes int layoutId, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new BaseViewHolder(view);
    }

    public static BaseViewHolder createViewHolder(View view) {
        return new BaseViewHolder(view);
    }


    public BaseViewHolder setText(@IdRes int viewId, String s) {
        TextView text = getView(viewId);
        text.setText(s);
        return this;
    }

    public BaseViewHolder setTextSize(@IdRes int viewId, int size) {
        TextView text = getView(viewId);
        text.setTextSize(size);
        return this;
    }

    public BaseViewHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView text = getView(viewId);
        text.setTextColor(color);
        return this;
    }

    public BaseViewHolder setTextColor(@IdRes int viewId, ColorStateList color) {
        TextView text = getView(viewId);
        text.setTextColor(color);
        return this;
    }

    public BaseViewHolder setImageDrawable(@IdRes int viewId, @SuppressLint("SupportAnnotationUsage") @DrawableRes Drawable drawable) {
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setVisible(@IdRes int viewId, boolean b) {
        getView(viewId).setVisibility(b ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseViewHolder setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        return this;
    }


}
