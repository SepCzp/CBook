package com.example.czp.cookbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.utils.Constan;
import com.example.czp.cookbook.utils.UIUtils;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public class MyPageAdpater extends PagerAdapter {


    private final Context context;

    public MyPageAdpater(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = UIUtils.inflate(context, R.layout.banner);
        ImageView img_banner = view.findViewById(R.id.img_banner);
        int count = position % Constan.ICON.length;
        Glide.with(context).load(Constan.ICON[count])
                .apply(RequestOptions.centerCropTransform())
                .into(img_banner);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE/2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
