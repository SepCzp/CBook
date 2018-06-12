package com.example.czp.cookbook.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.czp.cookbook.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by chenzipeng on 2018/6/8.
 * function:
 */
public abstract class BaseAdapter<T, VM extends BaseViewHolder> extends RecyclerView.Adapter<VM> {

    public static final int HEAD_VIEW = 0x00000111;//
    public static final int FOOTER_VIEW = 0x00000222;//
    public static final int LOADING_VIEW = 0x00000666;//
    private LoadMoreView loadMore = new LoadMoreView();
    private int layoutId;
    public List<T> data;
    private LinearLayout headerView;
    private LinearLayout footerView;
    private boolean isOpenLoad = true;
    private LoadMoreListener loadMoreListener;
    private OnItemClickListener onItemClickListener;

    public BaseAdapter(@LayoutRes int layoutId) {
        this(layoutId, null);
    }

    public BaseAdapter(int layoutId, @Nullable List<T> data) {
        this.layoutId = layoutId;
        this.data = data == null ? new ArrayList<T>() : data;
    }

    public BaseAdapter(@Nullable List<T> data) {
        this(0, data);
    }


    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (type == HEAD_VIEW) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (type == FOOTER_VIEW) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(VM holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (type == HEAD_VIEW || type == FOOTER_VIEW) {
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                p.setFullSpan(true);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (getHeaderViewCount() > position) {
            return HEAD_VIEW;
        }
        if (position - getHeaderViewCount() < data.size()) {
            return super.getItemViewType(position);
        } else {
            int numFooterViews = getFooterViewCount();
            if (numFooterViews > position - getHeaderViewCount() - data.size()) {
                return FOOTER_VIEW;
            } else {
                return LOADING_VIEW;
            }
        }
    }

    @NonNull
    @Override
    public VM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VM holder = null;
        switch (viewType) {
            case HEAD_VIEW:
                holder = (VM) BaseViewHolder.createViewHolder(headerView);//头
                break;
            case FOOTER_VIEW:
                holder = (VM) BaseViewHolder.createViewHolder(footerView);//尾
                break;
            case LOADING_VIEW://加载
                holder = (VM) BaseViewHolder.createViewHolder(R.layout.load_layout, parent);
                break;
            default://item
                holder = (VM) BaseViewHolder.createViewHolder(layoutId, parent);
                break;
        }
        holder.setAdapter(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VM holder, final int position) {
        int itemViewType = holder.getItemViewType();
        autoLoadMore(position);
        switch (itemViewType) {
            case HEAD_VIEW:
                break;
            case FOOTER_VIEW:
                break;
            case LOADING_VIEW:
                loadMore.convert(holder);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (loadMore.getState() == LoadMoreView.FAIL_STATE) {
                            loadMore.setState(LoadMoreView.LOADING_STATE);
                            notifyItemChanged(getLoadMorePosition());
                        }
                    }
                });
                break;
            default:
                convert(holder, position - getHeaderViewCount());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemClickListener!=null){
                            onItemClickListener.itemClick(v,position - getHeaderViewCount());
                        }
                    }
                });
                break;
        }
    }

    /**
     * 自动刷新 调用接口
     *
     * @param position
     */
    private void autoLoadMore(int position) {
        if (loadMore.getState() == LoadMoreView.FAIL_STATE || loadMore.getState() == LoadMoreView.END_STATE) {
            return;
        }
        if (getLoadMorePosition() == 0) {
            return;
        }
        if (!isOpenLoad) {
            return;
        }
        if (position < getItemCount() - 1) {
            return;
        }
        loadMore.setState(LoadMoreView.LOADING_STATE);
        if (loadMoreListener != null) {
            loadMoreListener.onLoadMore();
        }
    }

    private int getLoadMorePosition() {
        return data.size() + getHeaderViewCount() + getFooterViewCount();
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete() {
        loadMore.setState(LoadMoreView.OTHER_STATE);
        notifyItemChanged(getLoadMorePosition());
    }

    /**
     * 加载失败
     */
    public void loadMoreFail() {
        loadMore.setState(LoadMoreView.FAIL_STATE);
        notifyItemChanged(getLoadMorePosition());
    }

    /**
     * 加载完成 没有数据
     */
    public void loadMoreEnd() {
        loadMore.setState(LoadMoreView.END_STATE);
        notifyItemChanged(getLoadMorePosition());
    }

    /**
     * 开启加载功能
     */
    public void openLoadMore() {
        loadMore.setState(LoadMoreView.LOADING_STATE);
        isOpenLoad=true;
    }

    /**
     * 关闭LoadMoreView中的功能
     */
    public void closeLoadMoreFeatures() {
        loadMore.setState(LoadMoreView.OTHER_STATE);
        isOpenLoad = false;
    }

    @Override
    public int getItemCount() {
        return data.size() + getHeaderViewCount() + getFooterViewCount() + getLoadMoreSeat();
    }

    public abstract void convert(VM holder, int position);



    public void setData(List<T> ts) {
        this.data = ts == null ? new ArrayList<T>() : ts;
        notifyDataSetChanged();
    }

    public void addData(Collection<? extends T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(T t) {
        data.add(t);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        data.remove(t);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }


    private void addHeaderView(View view, int index, int orientation) {
        if (headerView == null) {
            headerView = new LinearLayout(view.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                headerView.setOrientation(LinearLayout.VERTICAL);
                headerView.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            } else {
                headerView.setOrientation(LinearLayout.HORIZONTAL);
                headerView.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            }
        }

        int childCount = headerView.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        headerView.addView(view, index);
    }

    public void addHeaderView(View view, int index) {
        addHeaderView(view, index, LinearLayout.VERTICAL);
    }

    public void addHeaderView(View view) {
        addHeaderView(view, -1, LinearLayout.VERTICAL);
    }

    public void removeHeaderView() {
        if (headerView == null || headerView.getChildCount() == 0) {
            return;
        }
        headerView.removeAllViews();
        notifyItemRemoved(0);
    }

    public void removeHeaderView(int index) {
        if (headerView == null || headerView.getChildCount() == 0) {
            return;
        }
        if (headerView.getChildCount() == 1) {
            removeHeaderView();
            return;
        }

//        if(index>headerView.getChildCount()){
//            throw new IndexOutOfBoundsException("This index is greater than" +
//                    " the number of subviews in the view");
//        }
        headerView.removeViewAt(index);
    }

    private int getHeaderViewCount() {
        if (headerView == null || headerView.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    private void addFooterView(View view, int index, int orientation) {
        if (footerView == null) {
            footerView = new LinearLayout(view.getContext());
            if (orientation == LinearLayout.VERTICAL) {
                footerView.setOrientation(LinearLayout.VERTICAL);
                footerView.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            } else {
                footerView.setOrientation(LinearLayout.HORIZONTAL);
                footerView.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            }
        }

        int childCount = footerView.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }

        // notifyItemInserted(getHeaderViewCount() + data.size() + getFooterViewCount() + getLoadMoreSeat());
        footerView.addView(view, index);
    }

    public void addFooterView(View view) {
        addFooterView(view, -1, LinearLayout.VERTICAL);
    }

    public void addFooterView(View view, int index) {
        addFooterView(view, index, LinearLayout.VERTICAL);
    }

    private int getFooterViewCount() {
        if (footerView == null || footerView.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public void removeFooterView() {
        if (footerView == null || footerView.getChildCount() == 0) {
            return;
        }
        footerView.removeAllViews();
        notifyItemRemoved(getHeaderViewCount() + data.size() + getFooterViewCount());
    }

    public void removeFooterView(int index) {
        if (footerView == null || footerView.getChildCount() == 0) {
            return;
        }
        if (footerView.getChildCount() == 0) {
            removeFooterView();
            return;
        }

        footerView.removeViewAt(index);
    }

    private int getLoadMoreSeat() {
        int count = 0;
        if (isOpenLoad) {
            count += 1;
        }
        return count;
    }

    public interface LoadMoreListener {

        /**
         * 加载
         * */
        void onLoadMore();


    }

    public interface OnItemClickListener{
        void itemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}
