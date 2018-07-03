package com.example.czp.cookbook.ui.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.example.czp.cookbook.utils.UIUtils;

/**
 * Created by chenzipeng on 2018/7/3.
 * function:
 */
public class SpringBackView extends ScrollView {

    private static final String TAG = SpringBackView.class.getSimpleName();
    private View recyclerView;
    private Rect rect = new Rect();//保存原来的位置
    private float y;

    public SpringBackView(Context context) {
        super(context, null);
    }

    public SpringBackView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SpringBackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //获取子控件数量
        if (getChildCount() > 0) {
            recyclerView =  getChildAt(0);
        }
        setOverScrollMode(OVER_SCROLL_NEVER);//取消5.0效果
    }

    /**
     * 当recyclerView显示第一个条目和最后一个条目允许做弹性 设置为true 让父控件消费onTouchEvent事件
     * 否则 super.onInterceptTouchEvent(ev)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                final float preY = y;
                float nowY = ev.getY();
                int deltaY;//阻尼值
                deltaY = (int) (Math.abs(nowY - preY) * 0.1);
                if(isNeedMove()){
                    if (rect.isEmpty()) {
                        rect.set(recyclerView.getLeft(), recyclerView.getTop(), recyclerView.getRight(), recyclerView.getBottom());
                    }
                    if (preY < nowY) { //下拉
                        recyclerView.layout(rect.left, rect.top + deltaY, rect.right, rect.bottom + deltaY);
                    } else if (preY > nowY) {//上拉
                        recyclerView.layout(rect.left, rect.top - deltaY, rect.right, rect.bottom - deltaY);
                    }
                }

                break;
            case MotionEvent.ACTION_UP://抬起
                if(isNeedMove()){
                    recover();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void RefreshAnimationFinish(){
        //TranslateAnimation animation=new TranslateAnimation()
    }

    public void recover() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, recyclerView.getTop(), rect.top);
        Interpolator in = new DecelerateInterpolator();
        animation.setInterpolator(in);
        animation.setDuration(300);
        recyclerView.startAnimation(animation);
        recyclerView.layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    private boolean isNeedMove(){
        return getScrollY()==0||getScrollY()>=recyclerView.getMeasuredHeight()- UIUtils.getScreenHeight();
    }


}
