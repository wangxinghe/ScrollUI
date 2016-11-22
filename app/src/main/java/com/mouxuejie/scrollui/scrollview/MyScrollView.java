package com.mouxuejie.scrollui.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by wangxinghe1 on 2016/11/14.
 */

public class MyScrollView extends ScrollView {
    private int downY;
    private int mTouchSlop;

    public MyScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int)ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(downY - (int)ev.getRawY()) > mTouchSlop) {
                    return true;
                }
                break;
        }
        downY = (int)ev.getRawY();
        return super.onInterceptTouchEvent(ev);
    }
}
