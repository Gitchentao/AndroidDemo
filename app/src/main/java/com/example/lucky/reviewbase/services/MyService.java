package com.example.lucky.reviewbase.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.lucky.reviewbase.Main2Activity;
import com.example.lucky.reviewbase.R;

public class MyService extends Service {


    private final String TAG = "MyService";
    private DownloadBinder binder = new DownloadBinder();

    public MyService() {
    }


    public class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "开始下载");
        }

        public int getProgress() {
            Log.d(TAG, "进度条");
            return 0;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, Main2Activity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setContentText("test")
                .setContentTitle("test")
                .setWhen(System.currentTimeMillis()) // 通知时间
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true) // 点击后是否通知消失
                .setLights(Color.WHITE, 1000, 1000)//呼吸灯
                .setContentIntent(pi)
                .build();
        startForeground(10, notification);
        Log.d(TAG, "创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "开始执行任务");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "停止");
    }
}
