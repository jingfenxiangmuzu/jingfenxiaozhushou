package com.example.jingfenxiaozhushou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 这个类实现各种用户界面
 *
 */

public class UserView extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //形成初始布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //三个按钮
        Button button_importNewreport=(Button) findViewById(R.id.button_importNewVideo);
        button_importNewreport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("data_return","importNewreport");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        Button button_checkReport=(Button) findViewById(R.id.button_checkReport);
        button_checkReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("data_return","checkReport");
                setResult(RESULT_OK,intent);
                finish();

            }
        });

        Button button_shotNewVideo=(Button) findViewById(R.id.button_shotNewVideo);
        button_shotNewVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("data_return","shotNewVideo");
                setResult(RESULT_OK,intent);
                finish();

            }
        });

        Button button_history=(Button) findViewById(R.id.button_history);
        button_history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent();
                intent.putExtra("data_return","history");
                setResult(RESULT_OK,intent);
                finish();

            }
        });

    }


}
