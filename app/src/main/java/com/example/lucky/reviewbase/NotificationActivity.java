package com.example.lucky.reviewbase;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


/**
 * 通知使用
 */
public class NotificationActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button startnotifcation = findViewById(R.id.startnotifcation);

        startnotifcation.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
//
//        manager.createNotificationChannel(channel);
        Intent intent = new Intent(this, Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setContentTitle("aaa") // 内容标题
                .setContentText("bbbbb") // 内容正文
                .setWhen(System.currentTimeMillis()) // 通知时间
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent) // 点击跳转
                .setAutoCancel(true) // 点击后是否通知消失
                .setLights(Color.WHITE, 1000, 1000)//呼吸灯
                .build();
        manager.notify(1, notification);

    }
}
