package com.example.hp.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.io.InputStream;

/**
 * Created by HP on 2016/10/14.
 */
public class DynamicReceiver extends BroadcastReceiver{
    private static final String DYNAMICACTION="com.example.lab4.dynamicreceiver";//约定俗成，包名+intent.action+MyReceiver
    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals(DYNAMICACTION)){//对Action进行判断
            Bundle bundle=intent.getExtras();
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_widget);//创建新的RemoteView
            String message = bundle.getString("message");//获取输入的信息。
            remoteViews.setImageViewResource(R.id.widget_image,R.mipmap.dynamic);//设置RemoteView中，ImageView的Resourse Id
            remoteViews.setTextViewText(R.id.widget_name, message);//设置RenoteView中，textview中的内容
            // Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.mipmap.dynamic);
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), NewWidget.class), remoteViews);
            /*NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder=new Notification.Builder(context);
            builder.setContentTitle("动态广播")
                    .setContentText(bundle.getString("message"))
                    .setLargeIcon(bitmap)
                    .setTicker("您有一条新信息.")
                    .setSmallIcon(R.mipmap.dynamic)
                    .setAutoCancel(false);
            Intent intent2 = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent2, 0);
            builder.setContentIntent(contentIntent);
            Notification notify=builder.build();
            notificationManager.notify(0,notify);*/
        }
    }
}
