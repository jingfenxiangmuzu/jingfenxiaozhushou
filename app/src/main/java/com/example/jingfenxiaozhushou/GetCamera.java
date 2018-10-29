package com.example.jingfenxiaozhushou;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/**
 * Created by Asus on 2018/4/24.
 * 目前不需要这个类
 */

public class GetCamera extends AppCompatActivity {
    private static int CAMERA_OK=1;
    private static int REQUEST_EXTERNAL_STORAGE=1;
    private VideoView videoView;
    private Uri videoUri;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        getPermission();
        videoView = (VideoView)this.findViewById(R.id.video_view);
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
    public void start(View v) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, 1);
    }

        private void getPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(GetCamera.this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(GetCamera.this,
                        new String[]{android.Manifest.permission.CAMERA}, CAMERA_OK);

            }
            int permission = ActivityCompat.checkSelfPermission(GetCamera.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(GetCamera.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }

            permission = ActivityCompat.checkSelfPermission(GetCamera.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(GetCamera.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }
            permission = ActivityCompat.checkSelfPermission(GetCamera.this,
                    android.Manifest.permission.RECORD_AUDIO);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(GetCamera.this, new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_EXTERNAL_STORAGE);
            }
        } else {
//这个说明系统版本在6.0之下，不需要动态获取权限。

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK)
        {
            videoUri = data.getData();
        }
    }

}




