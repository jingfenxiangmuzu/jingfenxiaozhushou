package com.example.jingfenxiaozhushou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Asus on 2018/5/21.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_REPORT = "create table Report("
            +"id integer primary key autoincrement,"//主键
            //+"name text,"//暂时不需要名字
            +"time real,"//时间
            +"deformityRate real,"//畸形率
            +"density real,"//密度
            +"rateOfSurvival real)";//存活率
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_REPORT);
        Toast.makeText(mContext,"Create succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists Report");
        onCreate(db);
    }
    /*
    * 打印所有的数据*/
    public String[] showMyData(SQLiteDatabase db){
        String[] ids;
        Cursor cursor = db.query("Report",null,null,null,null,null,null);
        ids=new String[cursor.getCount()];
        int i=0;
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

                ids[i]=String.valueOf(id);
                i++;
            }while(cursor.moveToNext());
        }
        return ids;
    }
    /*
    * 用id寻找记录*/
    public Report findReportById(SQLiteDatabase db,int findId){
        Cursor cursor = db.query("Report",null,null,null,null,null,null);
        Report report;
        report = new Report();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                if(findId==id) {
                    //遍历Cursor对象，取出数据并打印

                    double time = cursor.getDouble(cursor.getColumnIndex("time"));
                    double deformityRate = cursor.getDouble(cursor.getColumnIndex("deformityRate"));
                    double density = cursor.getDouble(cursor.getColumnIndex("density"));
                    double rateOfSurvival = cursor.getDouble(cursor.getColumnIndex("rateOfSurvival"));
                    Log.d("MainActivity", "report id is " + id);
                    Log.d("MainActivity", "report time is " + time);
                    Log.d("MainActivity", "report deformityRate is " + deformityRate);
                    Log.d("MainActivity", "report density is " + density);
                    Log.d("MainActivity", "report rateOfSurvival is " + rateOfSurvival);
                    report.setId(id);
                    report.setTime(time);
                    report.setDeformityRate(deformityRate);
                    report.setDensity(density);
                    report.setRateOfSurvival(rateOfSurvival);
                }
            }while(cursor.moveToNext());
        }
        return report;
    }
    /*
    增加一组记录*/
    public void insert_report(SQLiteDatabase db,double time, double deformityRate, double density, double rateOfSurvival){
        ContentValues values = new ContentValues();
        //组建一组数据
        values.put("time",time);
        values.put("deformityRate",deformityRate);
        values.put("density",density);
        values.put("rateOfSurvival",rateOfSurvival);
        db.insert("Report",null,values);
        values.clear();
    }
    /**
     * 利用id删除一组记录
     */

    public void deleteById(SQLiteDatabase db,int deleteId){
        String whereClasuse = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(deleteId)};
        db.delete("Report",whereClasuse,whereArgs);
    }
}
