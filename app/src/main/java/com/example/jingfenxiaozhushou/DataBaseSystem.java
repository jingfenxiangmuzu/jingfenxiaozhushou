package com.example.jingfenxiaozhushou;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Asus on 2018/4/24.
 */

public class DataBaseSystem extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasesystem_layout);
        dbHelper = new MyDatabaseHelper(this,"Reports.db",null,1);
        Button createDatebase = (Button) findViewById(R.id.create_datebase);
        createDatebase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.getWritableDatabase();
            }
        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Report表中所有的数据
                Cursor cursor = db.query("Report",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        double live_rate = cursor.getDouble(cursor.getColumnIndex("live_rate"));
                        Log.d("MainActivity", "report name is " + name);
                        Log.d("MainActivity", "report live rate is " + live_rate);
                    }while(cursor.moveToNext());
                }
            }
        });
        //结束该活动回到control类然后重启uesrview
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
