package com.example.administrator.activitylifecycletest;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(MainActivity.TAG, "MainActivity onCreate.");

        Button startNormalActivity = (Button)findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button)findViewById(R.id.start_Dialog_activity);

        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(MainActivity.TAG,"MainActivity onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(MainActivity.TAG,"MainActivity onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(MainActivity.TAG,"MainActivity onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(MainActivity.TAG,"MainActivity onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(MainActivity.TAG,"MainActivity onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(MainActivity.TAG,"MainActivity onRestart");
    }
}
