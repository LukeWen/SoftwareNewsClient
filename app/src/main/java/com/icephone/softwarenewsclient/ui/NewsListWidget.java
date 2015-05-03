package com.icephone.softwarenewsclient.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.icephone.softwarenewsclient.R;
import com.icephone.softwarenewsclient.service.NewsClientRemoteViewsService;

public class NewsListWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        // Iterate over the array of active widgets.
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Set up the intent that starts the Earthquake
            // Remote Views Service, which will supply the views
            // shown in the List View.
            Intent intent = new Intent(context, NewsClientRemoteViewsService.class);
            // Add the app widget ID to the intent extras.
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

            // Instantiate the RemoteViews object for the App Widget layout.
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.news_widget);

            // Set up the RemoteViews object to use a RemoteViews adapter.
            views.setRemoteAdapter(R.id.newsListView, intent);

            // The empty view is displayed when the collection has no items.
            views.setEmptyView(R.id.newsListView, R.id.widget_empty_text);

            // Create a Pending Intent template to provide interactivity to
            // each item displayed within the collection View.
            Intent templateIntent = new Intent(context, MainActivity.class);
            templateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent templatePendingIntent =
                    PendingIntent.getActivity(context, 0, templateIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

            views.setPendingIntentTemplate(R.id.newsListView,
                    templatePendingIntent);

            // Notify the App Widget Manager to update the widget using
            // the modified remote view.
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
