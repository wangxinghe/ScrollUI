package com.mouxuejie.scrollui.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mouxuejie.scrollui.R;
import com.mouxuejie.scrollui.util.LogUtil;

/**
 * Created by wangxinghe1 on 2017/12/15.
 */

public class ListViewActivity extends Activity {

    public static void jump(Context context) {
        context.startActivity(new Intent(context, ListViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_layout);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ListViewAdapter());
    }

    class ListViewAdapter extends BaseAdapter {
        ListViewAdapter() {}

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public String getItem(int position) {
            return "Item " + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LogUtil.d("ListViewAdapter getView " + position);
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.layout_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mTv = (TextView) convertView.findViewById(R.id.item_tv);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mTv.setText(getItem(position));

            return convertView;
        }

        class ViewHolder {
            private TextView mTv;
        }

    }

}
