package com.mouxuejie.scrollui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.mouxuejie.scrollui.staggrid.StaggeredGridViewAdapter;

/**
 * Created by wangxinghe1 on 2016/9/28.
 */

public class StaggeredGridActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StaggeredGridViewAdapter mStaggeredGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_recycler_view);
        mRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mStaggeredGridViewAdapter = new StaggeredGridViewAdapter();
        mRecyclerView.setAdapter(mStaggeredGridViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stagger_grid, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mStaggeredGridViewAdapter.addItem(1);
                break;
            case R.id.id_action_delete:
                mStaggeredGridViewAdapter.removeItem(2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
