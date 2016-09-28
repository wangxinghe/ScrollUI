package com.mouxuejie.scrollui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mLinearButton;
    private Button mGridButton;
    private Button mStaggeredGridButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearButton = (Button)findViewById(R.id.linear_recycler_view);
        mGridButton = (Button)findViewById(R.id.grid_recycler_view);
        mStaggeredGridButton = (Button)findViewById(R.id.staggered_grid_recycler_view);
        mLinearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }
}
