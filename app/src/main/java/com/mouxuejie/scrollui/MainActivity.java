package com.mouxuejie.scrollui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mouxuejie.scrollui.canvas.CanvasActivity;
import com.mouxuejie.scrollui.imageview.ImageActivity;
import com.mouxuejie.scrollui.scrollview.MyScrollViewActivity;

public class MainActivity extends AppCompatActivity {
    private Button mLinearButton;
    private Button mGridButton;
    private Button mStaggeredGridButton;
    private Button mImageViewButton;
    private Button mNestScrollButton;
    private Button mCanvasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearButton = (Button)findViewById(R.id.linear_recycler_view);
        mGridButton = (Button)findViewById(R.id.grid_recycler_view);
        mStaggeredGridButton = (Button)findViewById(R.id.staggered_grid_recycler_view);
        mImageViewButton = (Button)findViewById(R.id.image_view_button);
        mNestScrollButton = (Button)findViewById(R.id.nest_scroll_button);
        mCanvasButton = (Button)findViewById(R.id.canvas_button);
        mLinearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LinearActivity.class));
            }
        });
        mGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GridActivity.class));
            }
        });
        mStaggeredGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StaggeredGridActivity.class));
            }
        });
        mImageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });
        mNestScrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyScrollViewActivity.class));
            }
        });
        mCanvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
            }
        });
    }
}
