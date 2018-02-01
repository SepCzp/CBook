package com.example.czp.cookbook.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseActivity;
import com.example.czp.cookbook.ui.fragment.ClassifyFragment;
import com.example.czp.cookbook.ui.fragment.CookBookFragment;
import com.example.czp.cookbook.ui.fragment.MyFragment;
import com.example.czp.cookbook.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.rg_main)
    RadioGroup rg_main;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.id_title)
    RelativeLayout id_title;
    @BindView(R.id.tv_title)
    TextView tv_title;


    private List<Fragment> fragments;

    @Override
    protected void initView() {
        setStatus();
        initFragments();
        rb1.setChecked(true);
        id_title.setVisibility(View.GONE);
        FragmentUtils.addFragment(this, (CookBookFragment) fragments.get(0));
        rg_main.setOnCheckedChangeListener(this);
    }

    public void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(0, new CookBookFragment());
        fragments.add(1, new ClassifyFragment());
        fragments.add(2, new MyFragment());
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb1:
                FragmentUtils.switchFragment(this, fragments.get(0));
                ((CookBookFragment) fragments.get(0)).show();
                id_title.setVisibility(View.GONE);
                break;
            case R.id.rb2:
                FragmentUtils.switchFragment(this, fragments.get(1));
                ((ClassifyFragment) fragments.get(1)).show();
                id_title.setVisibility(View.VISIBLE);
                tv_title.setText("分类");
                break;
            case R.id.rb3:
                FragmentUtils.switchFragment(this, fragments.get(2));
                ((MyFragment) fragments.get(2)).show();
                tv_title.setText("个人");
                id_title.setVisibility(View.VISIBLE);
                break;
        }
    }
}
