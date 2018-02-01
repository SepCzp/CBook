package com.example.czp.cookbook.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.czp.cookbook.R;
import com.example.czp.cookbook.utils.UIUtils;

/**
 * Created by chenzipeng on 2018/1/17.
 * function:
 */

public abstract class SwitchFrameLayout extends FrameLayout {

    public static final int SUCCESS_STATE = 1;//
    public static final int LOADING_STATE = 0;//
    public static final int ERROE_STATE = 2;//

    /**
     * 默认状态
     */
    public int state = 0;

    private View succeedView;
    private View erroeView;
    private View loadingView;

    public SwitchFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwitchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //创建view
        initView();

    }

    private void initView() {
        if (erroeView == null) {
            erroeView = creatErroeView();
        }
        addView(erroeView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = creatLoadView();
        }
        addView(loadingView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //根据状态显示view
        showView();
    }

    private void showView() {
        if (loadingView != null) {
            loadingView.setVisibility(state == LOADING_STATE ? VISIBLE : GONE);
        }
        if (succeedView == null && state == SUCCESS_STATE) {
            succeedView = creatSucceedView();
            addView(succeedView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        if (erroeView != null) {
            erroeView.setVisibility(state == ERROE_STATE ? VISIBLE : GONE);
        }
    }

    public void show() {
        if (state == SUCCESS_STATE) {
            return;
        }
        if (state == ERROE_STATE || state == LOADING_STATE) {
            NetWork();
        }
        showView();
    }

    /**
     * 网络请求
     */
    protected abstract void NetWork();

    /**
     * 网络请求成功后创建view
     */
    protected abstract View creatSucceedView();

    /**
     * 请求网络失败view
     */
    private View creatErroeView() {
        View view = UIUtils.inflate(getContext(), R.layout.loading_error_page);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                state = LOADING_STATE;
                show();
            }
        });

        return view;
    }

    /**
     * 加载中view
     */
    private View creatLoadView() {
        return UIUtils.inflate(getContext(), R.layout.loading_page);
    }

    public void setState(Status state) {
        this.state = state.getValue();
        UIUtils.runUiThread(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });
    }


    public enum Status {
        success(SUCCESS_STATE), erroe(ERROE_STATE), load(LOADING_STATE);
        int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
