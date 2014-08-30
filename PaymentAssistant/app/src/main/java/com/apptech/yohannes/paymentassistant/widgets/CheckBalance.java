package com.apptech.yohannes.paymentassistant.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.apptech.yohannes.paymentassistant.R;
import com.apptech.yohannes.paymentassistant.core.BalanceCheckTask;
import com.apptech.yohannes.paymentassistant.core.ITask;

/**
 * Implementation of App Widget functionality.
 */
public class CheckBalance extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

@Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
        if(intent.getAction().equals("CheckBalance"))
        {
            ITask checkTask = new BalanceCheckTask(context);
            checkTask.Execute();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

     void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_check_balance);
        views.setOnClickPendingIntent(R.id.appwidget_text, GetSelfPendingIntent(context, "CheckBalance"));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private PendingIntent GetSelfPendingIntent(Context context, String action)
    {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return  PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}


