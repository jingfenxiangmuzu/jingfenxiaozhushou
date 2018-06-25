package com.example.jingfenxiaozhushou;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by momo on 18-6-25.
 */

public class detail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        ActionBar ab = getSupportActionBar();
        //使能app bar的导航功能
        ab.setDisplayHomeAsUpEnabled(true);
        //顶部返回按钮


    }
}