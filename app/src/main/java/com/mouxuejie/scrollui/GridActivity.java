package com.mouxuejie.scrollui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.mouxuejie.scrollui.grid.GridDividerItemDecoration;
import com.mouxuejie.scrollui.grid.GridRecyclerViewAdapter;
import com.mouxuejie.scrollui.listener.RecyclerViewTrackListener;

/**
 * Created by wangxinghe1 on 2016/9/28.
 */

public class GridActivity extends AppCompatActivity {
    private ImageView mBgIv;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mBgIv = (ImageView)findViewById(R.id.bg_iv);
        mRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(this));
        mRecyclerView.setAdapter(new GridRecyclerViewAdapter(mOnGetHeaderHeightListener, gridLayoutManager));
        mRecyclerView.addOnScrollListener(new RecyclerViewTrackListener(mOnGetHeaderHeightListener, mBgIv));
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
