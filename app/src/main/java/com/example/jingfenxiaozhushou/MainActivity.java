package com.example.jingfenxiaozhushou;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/*
   这个类就是我们的控制类，他是一个活动通过intent创造新的活动并利用intent从其他活动得到数据
    这个类没有布局，初始用户界面在UserView中
 */
public class MainActivity extends AppCompatActivity {

    String whichActivity;//从userview中返回要去哪个活动
    String videoPath;//从相册或者拍摄新的视频的uri

    private static int CAMERA_OK=1;//许可申请中用到的常量
    private static int REQUEST_EXTERNAL_STORAGE=1;//许可申请中用到的常量

    private static final String TAG="MainActivity";//打印日志用

    //各种活动的标签
    private static final int USER_VIEW = 1;
    private static final int VIDEO_SYSTEM = 2;
    private static final int DATABASE_SYSTEM = 3;
    private static final int CAMERA = 4;
    private static final int history = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //初始化时进入userview活动并生成用户界面
        Intent intent = new Intent("com.example.jingfenxiaozhushou.UserView");
        startActivityForResult(intent,USER_VIEW);
    }

    /*
    * 从各种活动返回时带来返回值集中在这个类里面处理
    * userview返回点击事件的结果
    * videoSystem返回选择的视频的uri
    * Camera返回拍摄的视频的uri
    * 其他未定
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent("com.example.jingfenxiaozhushou.UserView");
        switch (requestCode) {

            case USER_VIEW://看用户从main_layout点击的哪个按钮
                if (resultCode == RESULT_OK) {
                    whichActivity = data.getStringExtra("data_return");
                    Log.v("MainActivity",whichActivity);
                    whichaAtivityTo();

                }
                break;
            case VIDEO_SYSTEM:
                //得到系统来自VideoSystem数据
                if (resultCode == RESULT_OK) {
                    videoPath = data.getStringExtra("data_return");
                    Log.v("MainActivity",videoPath);

                }
                //回到userview界面
                startActivityForResult(intent,USER_VIEW);
                break;
            case DATABASE_SYSTEM:
                //得到系统来自DataBaseSystem数据

                //回到userview界面
                startActivityForResult(intent,USER_VIEW);
                break;
            case CAMERA:
                //得到系统来自Camera数据
                videoPath = data.getData().toString();
                Log.d("MainActivity",videoPath);
                //回到userview界面
                startActivityForResult(intent,USER_VIEW);
                break;
            case history :

                //回到userview界面
                startActivityForResult(intent,USER_VIEW);
                break;
        }

    }

/*
*判断去哪个活动，判断值来自于userView
**/
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
            //Intent intent = new Intent("com.example.jingfenxiaozhushou.GetCamera");
            //startActivityForResult(intent,CAMERA);
            getPermission();
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }
        else if(whichActivity.equals("history")){
            Intent intent = new Intent("com.example.jingfenxiaozhushou.history");
            startActivityForResult(intent,history);
        }

        whichActivity="";
    }
    /*
    * 数据库插入操作，一共需要插入四个数据
    * time 时间，rateOfSurvival成活率，deformityRate畸形率，density密度
    * 自动生成一个id作为主键
     * */
    private MyDatabaseHelper dbHelper;
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

    /*
    * 动态申请权限
    * 该函数还可以重构目前写的太杂乱
    *
    private static int CAMERA_OK=1;//许可申请中用到的常量
    private static int REQUEST_EXTERNAL_STORAGE=1;//许可申请中用到的常量
    * */
    private void getPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA}, CAMERA_OK);

            }
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }

            permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }
            permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.RECORD_AUDIO);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_EXTERNAL_STORAGE);
            }
        } else {
//这个说明系统版本在6.0之下，不需要动态获取权限。

        }
    }
}
