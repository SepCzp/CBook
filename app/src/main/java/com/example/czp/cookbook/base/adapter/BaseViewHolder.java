package com.example.czp.cookbook.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chenzipeng on 2018/1/18.
 * function:
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View converView;
    private Context context;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        views = new SparseArray<>();
        converView = itemView;
    }


    public <V extends View> V getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = converView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (V) view;
    }

    public static BaseViewHolder creatHolderView(Context context, ViewGroup parent, @LayoutRes int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(context, view);
        return viewHolder;
    }

    public BaseViewHolder setText(@IdRes int viewId, CharSequence charSequence) {
        TextView textView = getView(viewId);
        textView.setText(charSequence);
        return this;
    }

    public BaseViewHolder setTextSize(@IdRes int viewId, int size) {
        TextView textView = getView(viewId);
        textView.setTextSize(size);
        return this;
    }

    public BaseViewHolder setText(@IdRes int viewId, @StringRes int strid) {
        TextView textView = getView(viewId);
        textView.setText(strid);
        return this;
    }

    public BaseViewHolder setTextColor(@IdRes int viewId, int textColor) {
        TextView textView = getView(viewId);
        textView.setTextColor(textColor);
        return this;
    }

    public BaseViewHolder setImageResource(@IdRes int viewId, @DrawableRes int drawabId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(drawabId);
        return this;
    }

    public BaseViewHolder setBackgroudColor(@IdRes int viewId, @ColorInt int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int bgr) {
        View view = getView(viewId);
        view.setBackgroundResource(bgr);
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setImageDrawable(@IdRes int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     */
    public BaseViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnClickListener(View.OnClickListener listener) {
        converView.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnLongClickListener(View.OnLongClickListener listener) {
        converView.setOnLongClickListener(listener);
        return this;
    }


    public BaseViewHolder setTag(@IdRes int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }


    public BaseViewHolder setTag(@IdRes int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseViewHolder setAdapter(@IdRes int viewId, Adapter adapter) {
        AdapterView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }


    public BaseViewHolder setVisibility(@IdRes int viewId, boolean b) {
        View view = getView(viewId);
        view.setVisibility(b ? View.VISIBLE : View.GONE);
        return this;
    }
}
