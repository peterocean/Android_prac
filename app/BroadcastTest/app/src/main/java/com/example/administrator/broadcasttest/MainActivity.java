package com.example.administrator.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private  WifiChangerReceiver wifiChangerReceiver;
    private  int wifiOnOff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiChangerReceiver = new WifiChangerReceiver();
        registerReceiver(wifiChangerReceiver,intentFilter);   //注册广播
        Button button = (Button)findViewById(R.id.wifo_onoff);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager)getApplication().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
                    wifiManager.setWifiEnabled(false);
                } else {
                    wifiManager.setWifiEnabled(true);
                }
            }
        });
    }
    class WifiChangerReceiver extends  BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case WifiManager.WIFI_STATE_CHANGED_ACTION:
                    switch (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,WifiManager.WIFI_STATE_UNKNOWN)) {
                        case WifiManager.WIFI_STATE_DISABLING:
                            Toast.makeText(context,"Wifi disabling...",Toast.LENGTH_SHORT).show();
                            break;
                            case WifiManager.WIFI_STATE_DISABLED:
                            Toast.makeText(context,"Wifi is disabled.",Toast.LENGTH_SHORT).show();
                            break;
                        case WifiManager.WIFI_STATE_ENABLING:
                            Toast.makeText(context,"Wifi is enabling....",Toast.LENGTH_SHORT).show();
                            break;
                        case WifiManager.WIFI_STATE_ENABLED:
                            Toast.makeText(context,"Wifi is enabled.",Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(wifiChangerReceiver);
    }
}
