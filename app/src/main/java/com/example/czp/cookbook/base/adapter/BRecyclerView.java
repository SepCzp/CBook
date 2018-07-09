package com.example.czp.cookbook.base.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.czp.cookbook.R;

import java.util.List;


/**
 * Created by chenzipeng on 2018/6/11.
 * function:
 */
public class BRecyclerView extends FrameLayout {

    private LayoutInflater inflater;
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private int color;
    private onRefreshListener onRefreshListener;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> strings;

    public BRecyclerView(@NonNull Context context) {
        this(context, null);
        init(context);
    }

    public BRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public BRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BRecyclerView);
        color = typedArray.getColor(R.styleable.BRecyclerView_refresh_color, Color.BLUE);

        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.refresh_layout, this, false);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(false);
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout.setColorSchemeColors(color);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (onRefreshListener != null) {
                onRefreshListener.onRefresh();
            }
        });

        addView(view);
    }

    public interface onRefreshListener {
        void onRefresh();
    }

    public BRecyclerView setOnRefreshListener(BRecyclerView.onRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
        return this;
    }

    public BRecyclerView setRefresh(boolean b) {
        swipeRefreshLayout.setRefreshing(b);
        return this;
    }

    public BRecyclerView setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        return this;
    }

    public BRecyclerView setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        return this;
    }

    public BRecyclerView setIsAllowedRefresh(boolean b) {
        swipeRefreshLayout.setEnabled(b);
        return this;
    }
}
