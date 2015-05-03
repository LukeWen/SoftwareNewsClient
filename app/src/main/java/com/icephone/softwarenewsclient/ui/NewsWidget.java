package com.icephone.softwarenewsclient.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;

import com.icephone.softwarenewsclient.R;
import com.icephone.softwarenewsclient.service.NewsClientRemoteViewsService;

/**
 * Implementation of App Widget functionality.
 */
public class NewsWidget extends AppWidgetProvider {
    private Button settingsBtn;
    private Button refreshBtn;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //String widgetText = "TEST";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.news_widget);
        //views.setTextViewText(R.id.appwidget_title, widgetText);
        views.setTextViewText(R.id.appwidget_update_time, "2012-5-5 12:00:00");
        Intent intent = new Intent(context, NewsClientRemoteViewsService.class);
        views.setRemoteAdapter(R.id.newsListView, intent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
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
}

