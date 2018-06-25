package com.example.jingfenxiaozhushou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by momo on 18-6-25.
 */

public class history extends AppCompatActivity {

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(history.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

//        调用加载中页面
        //LoadingDialog.getInstance(this).show();
        //LoadingDialog.getInstance(this).dismiss();

    }


    //跳转到某一条记录的详情页
    public void next(View view) {
        Intent i = new Intent(this, detail.class);
        startActivity(i);
    }


}
