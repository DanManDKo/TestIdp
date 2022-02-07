package com.example.testidp.utils

import android.app.NotificationManager
import android.content.Context

fun Context.getNotificationsManager(): NotificationManager {
    return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}