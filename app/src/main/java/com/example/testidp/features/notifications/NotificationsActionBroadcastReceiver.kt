package com.example.testidp.features.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.testidp.main.MainActivity

class NotificationsActionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            action = intent.action
            intent.extras?.let { putExtras(it) }
        }
        context.startActivity(activityIntent)
    }
}