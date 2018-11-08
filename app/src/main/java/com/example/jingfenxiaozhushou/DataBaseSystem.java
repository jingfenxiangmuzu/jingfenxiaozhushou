package com.example.jingfenxiaozhushou;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Asus on 2018/4/24.
 */

public class DataBaseSystem extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    String[] ids;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databasesystem_layout);
        dbHelper = new MyDatabaseHelper(this,"Reports.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ids=dbHelper.showMyData(db);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DataBaseSystem.this, android.R.layout.simple_list_item_1);
        for(int i=0;i<ids.length;i++){
            adapter.add(ids[i]);
        }
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Report report = dbHelper.findReportById(db,Integer.parseInt(ids[i]));
                Toast.makeText(DataBaseSystem.this, "你点击的是" + i, Toast.LENGTH_SHORT).show();
                TextView textView_time = (TextView) findViewById(R.id.time);
                TextView textView_deformityRate = (TextView) findViewById(R.id.deformityRate);
                TextView textView_density = (TextView) findViewById(R.id.density);
                TextView textView_rateOfSurvival = (TextView) findViewById(R.id.rateOfSurvival);
                textView_time.setText("time:"+String.valueOf(report.getTime()));
                textView_deformityRate.setText("deformityRate:"+String.valueOf(report.getDeformityRate()));
                textView_density.setText("density"+String.valueOf(report.getDensity()));
                textView_rateOfSurvival.setText("rateOfSurvival"+String.valueOf(report.rateOfSurvival));
            }
        });

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

            }
        });
/**
 * 测试用于添加新的记录
 */

        Button insertTest = (Button)findViewById(R.id.insertTest);
        insertTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double time = Math.random()*100;
                double deformityRate = Math.random();
                double density = Math.random();
                double rateOfSurvival = Math.random();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.insert_report(db,time,deformityRate,density,rateOfSurvival);

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
