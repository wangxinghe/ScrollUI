package com.mouxuejie.scrollui.scrollview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mouxuejie.scrollui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxinghe1 on 2016/11/17.
 */

public class MyScrollViewActivity extends FragmentActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EditText mEditText;
    private Button mButton;
    private ViewPager mViewPager;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedscrv);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyScrollViewActivity.this, "haha", Toast.LENGTH_SHORT).show();
            }
        });
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        Fragment fragment1 = Fragment1.newInstance();
        Fragment fragment2 = Fragment1.newInstance();
        List<Fragment> list = new ArrayList<>();
        list.add(fragment1);
        list.add(fragment2);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));

        mHandler = new Handler();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private Fragment mCurrentFragment;

        protected List<Fragment> fragments;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        public void setFragments(List<Fragment> fragments) {
            this.fragments = fragments;
        }

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (mCurrentFragment != object) {
                mCurrentFragment = (Fragment)object;
            }
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            // LogUtils.i(TAG, "getItem position = " + position + " tag="
            // + fragments.get(position).getTag());
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
