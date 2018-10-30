package com.example.jingfenxiaozhushou;

import android.content.ContentValues;
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
        /*
        * 数据库查询功能，目前是遍历表并且打印
        * */
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Report表中所有的数据
                Cursor cursor = db.query("Report",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        double time = cursor.getDouble(cursor.getColumnIndex("time"));
                        double deformityRate = cursor.getDouble(cursor.getColumnIndex("deformityRate"));
                        double density = cursor.getDouble(cursor.getColumnIndex("density"));
                        double rateOfSurvival = cursor.getDouble(cursor.getColumnIndex("rateOfSurvival"));
                        Log.d("MainActivity", "report id is " + id);
                        Log.d("MainActivity", "report time is " + time);
                        Log.d("MainActivity", "report deformityRate is " + deformityRate);
                        Log.d("MainActivity", "report density is " + density);
                        Log.d("MainActivity", "report rateOfSurvival is " + rateOfSurvival);
                    }while(cursor.moveToNext());
                }
            }
        });

        Button insertTest = (Button)findViewById(R.id.insertTest);
        insertTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double time = Math.random()*100;
                double deformityRate = Math.random();
                double density = Math.random();
                double rateOfSurvival = Math.random();
                insert_report(time,deformityRate,density,rateOfSurvival);
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
    protected void insert_report(double time, double deformityRate, double density, double rateOfSurvival){
        dbHelper = new MyDatabaseHelper(this,"Reports.bd",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //组建一组数据
        values.put("time",time);
        values.put("deformityRate",deformityRate);
        values.put("density",density);
        values.put("rateOfSurvival",rateOfSurvival);
        db.insert("Report",null,values);
        values.clear();

    }
}
