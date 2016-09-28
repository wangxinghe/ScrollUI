package com.mouxuejie.scrollui.staggrid;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mouxuejie.scrollui.R;

import java.util.Random;

/**
 * Created by wangxinghe1 on 2016/9/28.
 */

public class StaggeredGridViewAdapter extends RecyclerView.Adapter {
    private static final int ITEM_COUNT = 60;
    private Random mRandom;
    private SparseIntArray mHeightSparseIntArray = new SparseIntArray();
    private SparseArray<String> mDataSparseArray = new SparseArray<>();

    public StaggeredGridViewAdapter() {
        initItemHeights();
        initData();
    }

    private void initItemHeights() {
        mRandom = new Random(100);
        for (int i = 0; i < ITEM_COUNT; i++) {
            mHeightSparseIntArray.put(i, (int)(200 + Math.random() * 500));
        }
    }

    private void initData() {
        for (int i = 0; i < ITEM_COUNT; i++) {
            mDataSparseArray.put(i, "Item " + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_staggered_grid_item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder)holder;
            itemHolder.itemView.getLayoutParams().height = mHeightSparseIntArray.get(position);
            itemHolder.mTv.setText(mDataSparseArray.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    public void addItem(int position) {
        mHeightSparseIntArray.put(position, (int)(200 + Math.random() * 500));
        mDataSparseArray.put(position, "Insert Item");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mHeightSparseIntArray.removeAt(position);
        mDataSparseArray.removeAt(position);
        notifyItemRemoved(position);
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        ItemHolder(View itemView) {
            super(itemView);
            mTv = (TextView)itemView.findViewById(R.id.item_tv);
            itemView.setBackgroundColor(0xff0000);
        }
    }

}
