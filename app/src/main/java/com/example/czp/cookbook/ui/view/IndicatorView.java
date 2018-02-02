package com.example.czp.cookbook.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.czp.cookbook.R;

/**
 * Created by chenzipeng on 2018/1/30.
 * function:
 */

public class IndicatorView extends View {

    private int radius;//半径
    private int count;//个数
    private int interval = 10;//每个圆点的间隔
    private int select = 0;
    private Paint paint;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        radius = (int) array.getDimension(R.styleable.IndicatorView_radio, 3);
        count = array.getInteger(R.styleable.IndicatorView_count, 5);

        array.recycle();
    }

    {
        paint = new Paint();
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //开始绘制的坐标
        int startW = ((getWidth() - (radius * 2) * count - count * (interval - 1)) - getPaddingLeft() - getPaddingRight()) / 2;
        int startH = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2;
        for (int i = 0; i < count; i++) {
            if (i == select) {
                paint.setColor(Color.BLACK);
                canvas.drawCircle(startW + (radius * 2) * (i) + interval * (i), startH, radius, paint);
            } else {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(startW + (radius * 2) * (i) + interval * (i), startH, radius, paint);
            }
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSelect(int select) {
        this.select = select;
        invalidate();
    }
}
