package com.example.lucky.reviewbase;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucky.reviewbase.services.MyService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button startserivce = findViewById(R.id.startserivce);
        Button stopserivce = findViewById(R.id.stopserivce);
        Button startbinderserivce = findViewById(R.id.startbinderserivce);
        Button unbinderserivce = findViewById(R.id.unbinderserivce);


        startserivce.setOnClickListener(this);
        stopserivce.setOnClickListener(this);
        startbinderserivce.setOnClickListener(this);
        unbinderserivce.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startserivce:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stopserivce:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.startbinderserivce:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbinderserivce:
                unbindService(connection);
                break;
            default:
        }
    }
}
