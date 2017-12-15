package com.mouxuejie.scrollui.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouxuejie.scrollui.R;
import com.mouxuejie.scrollui.linear.LinearDividerItemDecoration;
import com.mouxuejie.scrollui.util.LogUtil;

/**
 * Created by wangxinghe1 on 2017/12/15.
 */

public class RecyclerViewActivity extends Activity {

    public static void jump(Context context) {
        context.startActivity(new Intent(context, RecyclerViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_layout);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new LinearDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewAdapter());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
//                recyclerView.getAdapter().notifyDataSetChanged();
//                recyclerView.getAdapter().notifyItemRangeChanged(2, 5);
//                recyclerView.getAdapter().notifyItemRangeRemoved(2, 5);
            }
        });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter {
        RecyclerViewAdapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LogUtil.d("RecyclerViewAdapter onCreateViewHolder " + viewType);
            return new ItemViewHolder(LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.layout_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            LogUtil.d("RecyclerViewAdapter onBindViewHolder " + position);
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.mTv.setText("Item " + position);
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv;
        ItemViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView)itemView.findViewById(R.id.item_tv);
        }
    }

}
