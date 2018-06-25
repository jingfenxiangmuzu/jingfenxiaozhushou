package com.example.jingfenxiaozhushou;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
   这个类就是我们的控制类，他是一个活动通过intent创造新的活动并利用intent从其他活动得到数据

 */
public class MainActivity extends AppCompatActivity {
    String whichActivity;
    private static final int USER_VIEW = 1;
    private static final int VIDEO_SYSTEM = 2;
    private static final int DATABASE_SYSTEM = 3;
    private static final int CAMERA = 4;
    private static final int history = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //形成初始布局
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("com.example.jingfenxiaozhushou.UserView");
        startActivityForResult(intent,USER_VIEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent("com.example.jingfenxiaozhushou.UserView");
        switch (requestCode) {

            case USER_VIEW://看用户从main_layout点击的哪个按钮
                if (resultCode == RESULT_OK) {
                    whichActivity = data.getStringExtra("data_return");
                    Log.d("MainActivity",whichActivity);
                    whichaAtivityTo();

                }
                break;
            case VIDEO_SYSTEM:
                //得到系统来自VideoSystem数据

                //重启userview
                startActivityForResult(intent,USER_VIEW);
                break;
            case DATABASE_SYSTEM:
                //得到系统来自DataBaseSystem数据

                //重启userview
                startActivityForResult(intent,USER_VIEW);
                break;
            case CAMERA:
                //得到系统来自Camera数据

                //重启userview
                startActivityForResult(intent,USER_VIEW);
            case history :

                startActivityForResult(intent,USER_VIEW);
                break;
        }

    }

//判断点击事件来决定跳转
    protected void whichaAtivityTo(){


        if(whichActivity.equals("importNewreport")){
            Intent intent = new Intent("com.example.jingfenxiaozhushou.VideoSystem");
            startActivityForResult(intent,VIDEO_SYSTEM);

        }
        else if(whichActivity.equals("checkReport")){
            Intent intent = new Intent("com.example.jingfenxiaozhushou.DataBaseSystem");
            startActivityForResult(intent,DATABASE_SYSTEM);
        }
        else if(whichActivity.equals("shotNewVideo")){
            Intent intent = new Intent("com.example.jingfenxiaozhushou.Camera");
            startActivityForResult(intent,CAMERA);

        }
        else if(whichActivity.equals("history")){
            Intent intent = new Intent("com.example.jingfenxiaozhushou.history");
            startActivityForResult(intent,history);
        }

        whichActivity="";
    }
    //数据库插入操作
    private MyDatabaseHelper dbHelper;
    protected void insert_report(String name, double live_rate){
        dbHelper = new MyDatabaseHelper(this,"Reports.bd",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //组建一组数据
        values.put("name",name);
        values.put("live_rate",live_rate);
        db.insert("Book",null,values);
        values.clear();

    }


}
