package com.example.jingfenxiaozhushou;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
}
