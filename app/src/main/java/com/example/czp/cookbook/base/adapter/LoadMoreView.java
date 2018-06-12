package com.example.czp.cookbook.base.adapter;


import com.example.czp.cookbook.R;

/**
 * Created by chenzipeng on 2018/6/11.
 * function:
 */
public class LoadMoreView {

    public static final int LOADING_STATE = 0;//
    public static final int FAIL_STATE = 1;//
    public static final int END_STATE = 2;//
    public static final int OTHER_STATE = 3;//

    public static final int LOADING_VIEW = R.id.load_more_loading_view;//
    public static final int FAIL_VIEW = R.id.load_more_fail_view;//
    public static final int EDD_VIEW = R.id.load_more_end_view;//

    private int state = END_STATE;

    public void convert(BaseViewHolder holder) {
        switch (state) {
            case LOADING_STATE:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case FAIL_STATE:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case END_STATE:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case OTHER_STATE:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
        }
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean b) {
        holder.setVisible(EDD_VIEW, b);
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean b) {
        holder.setVisible(FAIL_VIEW, b);
    }

    private void visibleLoading(BaseViewHolder holder, boolean b) {
        holder.setVisible(LOADING_VIEW, b);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
