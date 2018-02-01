package com.example.czp.cookbook.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.czp.cookbook.R;

/**
 * Created by chenzipeng on 2018/1/29.
 * function:
 */

public class FoldingTextView extends LinearLayout implements View.OnClickListener {

    private TextView tv_content;
    private ImageView folding_imageview;
    private boolean isExpand = false;

    public FoldingTextView(Context context) {
        this(context, null);
    }

    public FoldingTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FoldingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        View view = View.inflate(context, R.layout.cookdetail_item_desc, null);
        tv_content = view.findViewById(R.id.tv_content);
        folding_imageview = view.findViewById(R.id.detail_desc_folding_imageview);
        folding_imageview.setOnClickListener(this);

        tv_content.getLayoutParams().height = initHeight();
        addView(view);
    }

    /**
     * 为什么不使用tv_content来设置maxlines和setlines
     * 使用tv_content来设置这些属性的话
     * 1.显示最大高度的时候，需要重新设置maxlines，不然最大行数为3行，tv_content伸缩不了
     * 2.显示的效果可能有瑕疵
     *
     * */
    public int initHeight() {
        int measuredWidth = tv_content.getMeasuredWidth();
        TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        int width = MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY);
        textView.setMaxLines(3);
        textView.setLines(3);
        int height = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        textView.measure(width, height);
        return textView.getMeasuredHeight();
    }

    public int maxHeight() {
        int measuredWidth = tv_content.getMeasuredWidth();
        int width = MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY);
        int height = MeasureSpec.makeMeasureSpec(2000, MeasureSpec.AT_MOST);
        tv_content.measure(width, height);
        return tv_content.getMeasuredHeight();
    }

    @Override
    public void onClick(View v) {
        expand();
    }

    public void expand() {
        int startHeight = 0;
        int endHeight = 0;
        if (!isExpand) {
            startHeight = initHeight();
            endHeight = maxHeight();
            isExpand = true;
        } else {
            startHeight = maxHeight();
            endHeight = initHeight();
            isExpand = false;
        }

        final ViewGroup.LayoutParams params = tv_content.getLayoutParams();

        ValueAnimator animator = ValueAnimator.ofInt(startHeight, endHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                params.height = value;
                tv_content.setLayoutParams(params);
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isExpand) {
                    folding_imageview.setBackgroundResource(R.drawable.ic_public_arrow_up);
                } else {
                    folding_imageview.setBackgroundResource(R.drawable.ic_public_arrow_down);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.setDuration(300);
        animator.start();

    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        tv_content.setText(text);
    }

}
