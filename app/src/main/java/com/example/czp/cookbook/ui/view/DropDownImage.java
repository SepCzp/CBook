package com.example.czp.cookbook.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by chenzipeng on 2018/1/22.
 * function:
 */

public class DropDownImage extends ScrollView {

    private View headImageView;
    private int dampValue = 100;
    private int startX;
    private int endX;
    private Rect rect;
    private boolean isAllowDamp = true;
    private boolean zoomed = false;
    private ViewGroup.LayoutParams params;
    private int scrY;
    private int height;


    public DropDownImage(Context context) {
        super(context);
    }

    public DropDownImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DropDownImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (getChildCount() > 0) {
            ViewGroup view = (ViewGroup) getChildAt(0);
            if (view != null && view instanceof ViewGroup) {
                headImageView = view.getChildAt(0);
                if(headImageView!=null){
                    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                    height = wm.getDefaultDisplay().getHeight();
                    if (rect == null) {
                        if (headImageView.getMeasuredHeight() > height / 3)
                            rect = new Rect(0, 0, headImageView.getMeasuredWidth(), height / 3);
                        else
                            rect = new Rect(0, 0, headImageView.getMeasuredWidth(), headImageView.getMeasuredHeight());
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("No child control");
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (zoomed) {
                    if (scrY > 0) {
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                restore();
                return false;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = (int) ev.getX();
                if (isAllowDamp && endX - startX > 0) {
                    blowUp();
                    zoomed = true;
                } else {
                    if (zoomed) {
                        restore();
                        zoomed = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //还原位置
                if (zoomed) {
                    restore();
                }
                break;
        }

        return super.onTouchEvent(ev);

    }

    public void blowUp() {
        int height = endX / dampValue;
        if (headImageView.getMeasuredHeight() > rect.bottom * 3 / 2) {
            height = 0;
        }

        ViewGroup.LayoutParams params = headImageView.getLayoutParams();
        params.height = headImageView.getMeasuredHeight() + height;
        headImageView.setLayoutParams(params);

    }


    public void restore() {
        final ViewGroup.LayoutParams params = headImageView.getLayoutParams();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(params.height, rect.bottom).setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                params.height = value;
                headImageView.setLayoutParams(params);
            }
        });
        valueAnimator.start();
        zoomed = false;

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        scrY = t;
        if (t == 0) {
            isAllowDamp = true;
            return;
        }
        if (t < rect.bottom) {

        }
        isAllowDamp = false;
    }
}
