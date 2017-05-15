package com.example.hp.lab4;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewWidget extends AppWidgetProvider {

    private AppWidgetManager appWidgetManager;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //在onUpdate中调用这个寒素
        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context,appWidgetManager,appWidgetIds);//表示这个函数从父类中继承
        this.appWidgetManager=appWidgetManager;
        Intent intent = new Intent(context, MainActivity.class);//从当前的实例跳转到MainActiviti的intent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);//延迟的inten，不是立即跳转。
        RemoteViews rv=new RemoteViews(context.getPackageName(),R.layout.new_widget);//
        //为按钮绑定事件处理器
        //第一个参数用来指定被绑定处理器的控件的ID，第二个参数用来指定当事件发生时，哪个PendingIntent将会被执行
        rv.setOnClickPendingIntent(R.id.widget_image,pendingIntent);//当点击图像时发生跳转。
        appWidgetManager.updateAppWidget(appWidgetIds,rv);//更新widget
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Bundle bundle=intent.getExtras();//获得传包含信息的Bundle
        if(intent.getAction().equals("com.example.lab4.staticreceiver")){////对Action进行判断
            RemoteViews rv=new RemoteViews(context.getPackageName(),R.layout.new_widget);//创建新的RemoteView
            rv.setImageViewResource(R.id.widget_image,bundle.getInt("id"));//设置RemoteView中，ImageView的Resourse Id
            rv.setTextViewText(R.id.widget_name,bundle.getString("name"));//设置RenoteView中，textview中的内容
            //通知App Widget Manager使用修改后的远程视图更新小组件，
            // getInstance（context）获得appwidgetManager，
            // ComponentName(context.getApplicationContext()，NewWidget.class）获得要修改的小组件（ComponentName）
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(), NewWidget.class), rv);

        }
    }
}

