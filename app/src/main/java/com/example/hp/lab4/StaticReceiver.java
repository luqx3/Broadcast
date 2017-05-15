package com.example.hp.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

/**
 * Created by HP on 2016/10/14.
 */
public class StaticReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        //由Icon的id得到Bitmap
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),bundle.getInt("id"));
        //通过getSystemService（）方法得到NotificationManager对象，它负责管理发送通知、清除通知等一系列的对通知的管理工作。
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(context);
        builder.setContentTitle("静态广播")//设置通知栏标题
                .setContentText(bundle.getString("name"))//设置通知栏显示内容
                .setLargeIcon(bitmap)//通知栏的大Icon
                .setTicker("您有一条新信息.")//通知首次出现在通知栏，带上升动画效果
                .setSmallIcon(bundle.getInt("id"))
                .setAutoCancel(true);//用户点击面板就让通知自动取消
        Intent intent2 = new Intent(context, MainActivity.class);//点击时跳转的intent，跳转到MainActivity
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent2, 0);//PendingIntent授予启动Mainactivity的权利。
        builder.setContentIntent(contentIntent);
        Notification notify=builder.build();//绑定通知，
        notificationManager.notify(0,notify);//显示通知
    }
}
