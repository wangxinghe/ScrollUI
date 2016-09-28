package com.mouxuejie.scrollui.grid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangxinghe1 on 2016/9/28.
 */

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public GridDividerItemDecoration(Context context) {
        resolveDivider(context);
    }

    private void resolveDivider(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontalDividers(c, parent);
        drawVerticalDividers(c, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (isLastRow(parent, itemPosition)) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if(isLastColumn(parent, itemPosition)) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }
    }

    private void drawHorizontalDividers(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVerticalDividers(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private int getSpanCount(RecyclerView parent) {
        return ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();
    }

    private boolean isLastRow(RecyclerView parent, int position) {
        int childCount = parent.getAdapter().getItemCount();
        boolean hasHeader = ((GridRecyclerViewAdapter)parent.getAdapter()).hasHeader();
        int itemCount = childCount - (hasHeader ? 1 : 0);
        int mod = itemCount % getSpanCount(parent);
        if (mod == 0) {
            return position >= childCount - getSpanCount(parent);
        } else {
            return position >= childCount - mod;
        }
    }

    private boolean isLastColumn(RecyclerView parent, int position) {
        boolean hasHeader = ((GridRecyclerViewAdapter)parent.getAdapter()).hasHeader();
        return (position + (hasHeader ? 0 : 1)) % getSpanCount(parent) == 0;
    }

}
