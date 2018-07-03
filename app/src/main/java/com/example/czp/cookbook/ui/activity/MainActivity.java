package com.example.czp.cookbook.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseActivity;
import com.example.czp.cookbook.ui.fragment.ClassifyFragment;
import com.example.czp.cookbook.ui.fragment.CookBookFragment;
import com.example.czp.cookbook.ui.fragment.MyFragment;
import com.example.czp.cookbook.utils.ActivityManager;
import com.example.czp.cookbook.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.id_title)
    RelativeLayout id_title;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    private List<Fragment> fragments;

    @Override
    protected void initView() {


        setStatus();
        initFragments();
        id_title.setVisibility(View.GONE);
        FragmentUtils.addFragment(this, (CookBookFragment) fragments.get(0));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        FragmentUtils.switchFragment(MainActivity.this, fragments.get(0));
                        ((CookBookFragment) fragments.get(0)).show();
                        id_title.setVisibility(View.GONE);
                        break;
                    case R.id.item2:
                        FragmentUtils.switchFragment(MainActivity.this, fragments.get(1));
                        ((ClassifyFragment) fragments.get(1)).show();
                        id_title.setVisibility(View.VISIBLE);
                        tv_title.setText("分类");
                        break;
                    case R.id.item3:
                        FragmentUtils.switchFragment(MainActivity.this, fragments.get(2));
                        ((MyFragment) fragments.get(2)).show();
                        tv_title.setText("个人");
                        id_title.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });
        setNoSkid(true);
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
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);

    }
}
