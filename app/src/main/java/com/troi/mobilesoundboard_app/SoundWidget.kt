package com.troi.mobilesoundboard_app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.RemoteViews

class SoundWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {

            val intent = Intent(context, SoundWidget::class.java).apply {
                action = "PLAY_SOUND"
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val views = RemoteViews(context.packageName, R.layout.widget_sound)
            views.setOnClickPendingIntent(R.id.playSoundButton, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "PLAY_SOUND") {
            val mediaPlayer = MediaPlayer.create(context, R.raw.vineboom)
            mediaPlayer.start()
        }
    }
}