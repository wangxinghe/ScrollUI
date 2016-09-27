package com.mouxuejie.scrollui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouxuejie.scrollui.scroll.RecyclerViewTrackListener;

public class MainActivity extends AppCompatActivity {
    private ImageView mBgIv;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBgIv = (ImageView)findViewById(R.id.bg_iv);
        mRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapter());
        mRecyclerView.addOnScrollListener(new RecyclerViewTrackListener(mOnGetHeaderHeightListener, mBgIv));
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter {
        private static final int VIEW_TYPE_HEADER = 0;
        private static final int VIEW_TYPE_ITEM = 1;

        RecyclerViewAdapter() {
            super();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_HEADER;
            } else {
                return VIEW_TYPE_ITEM;
            }
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
            return 30;
        }

        class HeaderHolder extends RecyclerView.ViewHolder {

            HeaderHolder(final View headerView) {
                super(headerView);
                headerView.getViewTreeObserver().addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mOnGetHeaderHeightListener.onGetHeaderHeight(headerView.getHeight());
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

    RecyclerViewTrackListener.OnGetHeaderHeightListener mOnGetHeaderHeightListener
            = new RecyclerViewTrackListener.OnGetHeaderHeightListener() {
        int mHeaderHeight;

        @Override
        public void onGetHeaderHeight(int height) {
            //设置背景初始位移,位于Header下面
            mHeaderHeight = height;
            mBgIv.setTranslationY(height);
        }

        @Override
        public int getHeaderHeight() {
            return mHeaderHeight;
        }
    };

}
