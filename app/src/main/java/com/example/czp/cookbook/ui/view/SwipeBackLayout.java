package com.example.czp.cookbook.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by chenzipeng on 2018/6/4.
 * function:
 */
public class SwipeBackLayout extends FrameLayout {

    private ViewDragHelper helper;
    private int currentEdgeFlags;
    private int currentX;
    private MyOnScrollListener mOnScrollListener;
    private boolean isNoSkid = false;

    public SwipeBackLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        helper = ViewDragHelper.create(this, 1.0f, new ViewDragCallBack());
        helper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    /**
     * 默认不拦截事件
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isNoSkid?super.onInterceptTouchEvent(ev):helper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (helper.continueSettling(true)) {
            invalidate();
        }
    }

    private class ViewDragCallBack extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        /**
         * 位置改变
         * */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (currentEdgeFlags == ViewDragHelper.EDGE_LEFT) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.complete((float) (Math.abs(left) * 1.00 / getWidth()));
                    if (left >= getWidth()) {
                        finish();
                    }
                }
            }
        }

        /**
         * 释放
         * */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (currentEdgeFlags == ViewDragHelper.EDGE_LEFT) {
                if (currentX > getWidth() / 2) {
                    helper.settleCapturedViewAt(getWidth(), 0);
                } else {
                    helper.settleCapturedViewAt(0, 0);
                }
                invalidate();//刷新UI
            }
        }

        /**
         *
         * */
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            currentEdgeFlags = edgeFlags;
            helper.captureChildView(getChildAt(0), pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            currentX = left > 0 ? left : 0;
            return currentX;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }
    }

    private void finish() {
        Activity activity = (Activity) getContext();
        activity.finish();
        activity.overridePendingTransition(0, android.R.anim.fade_out);
    }

    public interface MyOnScrollListener {
        void complete(float i);
    }

    public void setMyOnScrollListener(MyOnScrollListener mOnScrollListener) {
        this.mOnScrollListener = mOnScrollListener;
    }

    public void setNoSkid(boolean noSkid) {
        isNoSkid = noSkid;
    }
}
