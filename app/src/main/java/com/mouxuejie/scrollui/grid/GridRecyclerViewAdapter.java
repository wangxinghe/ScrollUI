package com.mouxuejie.scrollui.grid;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.mouxuejie.scrollui.R;
import com.mouxuejie.scrollui.listener.RecyclerViewTrackListener;

/**
 * Created by wangxinghe1 on 2016/9/28.
 */

public class GridRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private RecyclerViewTrackListener.OnGetHeaderHeightListener mOnGetHeaderHeightListener;

    public GridRecyclerViewAdapter(RecyclerViewTrackListener.OnGetHeaderHeightListener onGetHeaderHeightListener,
                                   final GridLayoutManager gridLayoutManager) {
        super();
        mOnGetHeaderHeightListener = onGetHeaderHeightListener;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isHeader(position) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? VIEW_TYPE_HEADER : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (VIEW_TYPE_HEADER == viewType) {
            return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header,
                    parent, false));
        } else {
            return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                    parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder)holder).mTv.setText(position + "");
        }
    }

    @Override
    public int getItemCount() {
        return 60;
    }

    public boolean hasHeader() {
        return true;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    class HeaderHolder extends RecyclerView.ViewHolder {

        HeaderHolder(final View headerView) {
            super(headerView);
            headerView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            if (mOnGetHeaderHeightListener != null) {
                                mOnGetHeaderHeightListener.onGetHeaderHeight(headerView.getHeight());
                            }
                            headerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTv;

        ItemHolder(View itemView) {
            super(itemView);
            mTv = (TextView)itemView.findViewById(R.id.item_tv);
        }
    }

}
