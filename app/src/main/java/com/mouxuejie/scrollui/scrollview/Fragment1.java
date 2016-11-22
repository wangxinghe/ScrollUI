package com.mouxuejie.scrollui.scrollview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mouxuejie.scrollui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxinghe1 on 2016/11/17.
 */

public class Fragment1 extends Fragment {
    private PaginationListView mListView;
    private Handler mHandler;
    private List<String> mList = new ArrayList<>();
    private ListViewAdapter mListViewAdapter;

    public static Fragment1 newInstance() {

        Bundle args = new Bundle();

        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.layout_1, null);
        mListView = (PaginationListView) contentView.findViewById(R.id.list_view);
        mListView.addHeaderView(LayoutInflater.from(contentView.getContext()).inflate(R.layout.layout_header, null));
        mListView.setOnLoadListener(new PaginationListView.OnLoadListener() {
            @Override
            public void onLoad() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 20; i < 40; i++) {
                            mList.add(String.valueOf(i));
                        }
                        mListViewAdapter.notifyDataSetChanged();
                        mListView.loadComplete();
                    }
                }, 3000);
            }
        });
        mListViewAdapter = new ListViewAdapter(combineList());
        mListView.setAdapter(mListViewAdapter);
        mHandler = new Handler();

        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<String> combineList() {
        for (int i = 0; i < 20; i++) {
            mList.add(String.valueOf(i));
        }
        return mList;
    }

    private class ListViewAdapter extends BaseAdapter {
        private List<String> mList;

        public ListViewAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public String getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewAdapter.ViewHolder holder;
            if (convertView == null) {
                holder = new ListViewAdapter.ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, null);
                holder.mTv = (TextView)convertView.findViewById(R.id.item_tv);
                convertView.setTag(holder);
            }
            holder = (ListViewAdapter.ViewHolder) convertView.getTag();
            holder.mTv.setText(getItem(position));

            return convertView;
        }

        private class ViewHolder {
            private TextView mTv;
        }
    }

}
