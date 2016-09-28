package com.mouxuejie.scrollui.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangxinghe on 27/9/2016.
 */

public class RecyclerViewTrackListener extends RecyclerView.OnScrollListener {

    //Header高度回调接口
    private OnGetHeaderHeightListener mOnGetHeaderHeightListener;
    //RecyclerView Y方向总的移动距离
    private int mTotalTranslationY;

    private View mTarget;

    public interface OnGetHeaderHeightListener {
        void onGetHeaderHeight(int height);
        int getHeaderHeight();
    }

    public RecyclerViewTrackListener(OnGetHeaderHeightListener onGetHeaderHeightListener, View target) {
        mOnGetHeaderHeightListener = onGetHeaderHeightListener;
        mTarget = target;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mTotalTranslationY += dy;

        //水印背景同步滑动距离为Header高度范围,超出该范围则保持不动
        int headerHeight = mOnGetHeaderHeightListener.getHeaderHeight();
        if (mTotalTranslationY < headerHeight) {
            mTarget.setTranslationY(headerHeight - mTotalTranslationY);
        } else {
            mTarget.setTranslationY(0);
        }
    }
}
