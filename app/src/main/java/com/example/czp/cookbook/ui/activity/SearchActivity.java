package com.example.czp.cookbook.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.czp.cookbook.R;
import com.example.czp.cookbook.adapter.HistroyAdapter;
import com.example.czp.cookbook.base.ui.BaseActivity;
import com.example.czp.cookbook.utils.SpUtils;
import com.example.czp.cookbook.utils.UIUtils;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {

    public static final String SEARCH_HISTORY = "search_history";//
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.recycle_history)
    RecyclerView recycle_history;
    private List<String> list;
    private HistroyAdapter adapter;

    @Override
    protected void initData() {
        super.initData();

        adapter = new HistroyAdapter();
        recycle_history.setAdapter(adapter);
        recycle_history.setLayoutManager(new LinearLayoutManager(this));
        recycle_history.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("name", list.get(position));
                goActivityData(intent, SearchResultActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = SpUtils.getInstance().getDataList(SEARCH_HISTORY, String.class);
        adapter.getData().clear();
        adapter.addData(list);
        if (list.size() > 0 && adapter.getHeaderLayoutCount() == 0) {
            adapter.addHeaderView(addHead());
            adapter.addFooterView(addFoot());
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        setStatus();

        tv_title.setVisibility(View.GONE);
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ed_search.setVisibility(View.VISIBLE);
        tv_search.setVisibility(View.VISIBLE);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    search();
                    return true;
                }
                return false;
            }
        });
    }


    public void search() {
        String trim = ed_search.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(SearchActivity.this, "搜索内容为空", Toast.LENGTH_SHORT).show();
        } else {

            if (!list.contains(trim)) {
                list.add(trim);
                SpUtils.getInstance().saveList(list, SEARCH_HISTORY);
            }

            Intent intent = new Intent();
            intent.putExtra("name", trim);
            goActivityData(intent, SearchResultActivity.class);
            ed_search.setText("");
        }
    }

    public View addHead() {
        View view = UIUtils.inflate(this, R.layout.head_foot_layout);
        TextView textView = view.findViewById(R.id.tv_head_and_foot);
        textView.setText("最近搜索");
        return view;
    }

    public View addFoot() {
        View view = UIUtils.inflate(this, R.layout.head_foot_layout);
        TextView textView = view.findViewById(R.id.tv_head_and_foot);
        textView.setText("清空历史");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                SpUtils.getInstance().remove(SEARCH_HISTORY);
                //  list.clear();
                adapter.removeAllFooterView();
                adapter.removeAllHeaderView();
            }
        });
        return view;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_search;
    }

}
