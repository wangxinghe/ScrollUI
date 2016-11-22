package com.mouxuejie.scrollui.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mouxuejie.scrollui.R;

import java.util.ArrayList;

/**
 * Created by wangxinghe1 on 2016/11/14.
 */

public class ScrollViewActivity extends Activity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
//    private PaginationListView mListView;
    private EditText mEditText;
    private Button mButton;
//    private List<String> mList = new ArrayList<>();
//    private Handler mHandler;
//    private ListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrv);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSwipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new FullLinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new LinearDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new RecyclerViewAdapter());

//        mListView = (PaginationListView) findViewById(R.id.list_view);
//        mListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header, null));
//        mListView.setOnLoadListener(new PaginationListView.OnLoadListener() {
//            @Override
//            public void onLoad() {
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 20; i < 40; i++) {
//                            mList.add(String.valueOf(i));
//                        }
//                        mListViewAdapter.notifyDataSetChanged();
//                        mListView.loadComplete();
//                    }
//                }, 3000);
//            }
//        });
//        mListViewAdapter = new ListViewAdapter(combineList());
//        mListView.setAdapter(mListViewAdapter);
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollViewActivity.this, "haha", Toast.LENGTH_SHORT).show();
            }
        });

//        mHandler = new Handler();
    }

//    private List<String> combineList() {
//        for (int i = 0; i < 20; i++) {
//            mList.add(String.valueOf(i));
//        }
//        return mList;
//    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter {

        private java.util.List<String> list = new ArrayList<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerViewAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                        parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof RecyclerViewAdapter.ItemHolder) {
                ((ItemHolder)holder).mTv.setText(position + "");
            }
        }

        @Override
        public int getItemCount() {
            return 300;
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            private TextView mTv;

            ItemHolder(View itemView) {
                super(itemView);
                mTv = (TextView)itemView.findViewById(R.id.item_tv);
            }
        }

    }

//    private class ListViewAdapter extends BaseAdapter {
//        private List<String> mList;
//
//        public ListViewAdapter(List<String> list) {
//            mList = list;
//        }
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public String getItem(int position) {
//            return mList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, null);
//                holder.mTv = (TextView)convertView.findViewById(R.id.item_tv);
//                convertView.setTag(holder);
//            }
//            holder = (ViewHolder) convertView.getTag();
//            holder.mTv.setText(getItem(position));
//
//            return convertView;
//        }
//
//        private class ViewHolder {
//            private TextView mTv;
//        }
//    }

}
