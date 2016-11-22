package com.mouxuejie.scrollui.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.mouxuejie.scrollui.R;

/**
 * Created by wangxinghe1 on 2016/11/17.
 */

public class PaginationListView extends ListView implements AbsListView.OnScrollListener {

    //底部View
    private View footerView;
    //ListView item个数
    int totalItemCount = 0;
    //最后可见的Item
    int lastVisibleItem = 0;
    //是否加载标示
    boolean isLoading = false;

    public PaginationListView(Context context) {
        super(context);
        initView(context);
    }

    public PaginationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PaginationListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 初始化ListView
     */
    private void initView(Context context){
        LayoutInflater mInflater = LayoutInflater.from(context);
        footerView = mInflater.inflate(R.layout.layout_footer, null);
        footerView.setVisibility(View.GONE);
        this.setOnScrollListener(this);
        //添加底部View
        this.addFooterView(footerView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (footerView == null || onLoadListener == null) return;

        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
        if(lastVisibleItem == totalItemCount) {
            if(!isLoading) {
                isLoading = true;
                //设置可见
                footerView.setVisibility(View.VISIBLE);
                //加载数据
                onLoadListener.onLoad();
            }
        }
    }

    private OnLoadListener onLoadListener;
    public void setOnLoadListener(OnLoadListener onLoadListener){
        this.onLoadListener = onLoadListener;
    }

    /**
     * 加载数据接口
     * @author Administrator
     *
     */
    public interface OnLoadListener{
        void onLoad();
    }

    /**
     * 数据加载完成
     */
    public void loadComplete(){
        footerView.setVisibility(View.GONE);
        isLoading = false;
        this.invalidate();
    }

}
