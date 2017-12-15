package com.mouxuejie.scrollui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mouxuejie.scrollui.page.ListViewActivity;
import com.mouxuejie.scrollui.page.RecyclerViewActivity;

/**
 * Created by wangxinghe1 on 2017/12/15.
 */

public class RecyclerVSListActivity extends Activity {

    public static void jump(Context context) {
        context.startActivity(new Intent(context, RecyclerVSListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_vs_list_layout);
        Button listviewBtn = (Button)findViewById(R.id.list_view);
        Button recyclerviewBtn = (Button)findViewById(R.id.recycler_view);
        listviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewActivity.jump(RecyclerVSListActivity.this);
            }
        });
        recyclerviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewActivity.jump(RecyclerVSListActivity.this);
            }
        });
    }
}
