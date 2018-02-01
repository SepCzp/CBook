package com.example.czp.cookbook.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.base.ui.BaseFragment;

import java.util.List;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public class FragmentUtils {

    public static void switchFragment(FragmentActivity context, Fragment to) {
        Fragment from = null;
        FragmentManager manager = context.getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                from = fragment;
            }
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (from != to) {
            if (!to.isAdded()) {
                transaction.add(R.id.frame, to, to.getClass().getName()).hide(from).commit();
            } else {
                transaction.show(to).hide(from).commit();
            }
        }
    }

    public static void goFragment(Fragment from,Fragment to){
        if(from!=null){
            FragmentTransaction transaction = from.getActivity().getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                transaction.add(R.id.frame, to, to.getClass().getName()).hide(from).commit();
            } else {
                transaction.show(to).hide(from).commit();
            }
        }
    }

    public static void addFragment(FragmentActivity activity, BaseFragment fragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, fragment, fragment.getClass().getName()).commit();
    }
}
