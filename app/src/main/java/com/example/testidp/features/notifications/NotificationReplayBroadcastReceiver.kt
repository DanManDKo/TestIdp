package com.example.testidp.features.notifications

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.testidp.main.MainActivity
import com.example.testidp.utils.constants.ExtraConstants
import com.example.testidp.utils.constants.KeysConstants

class NotificationReplayBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val userInputText = RemoteInput.getResultsFromIntent(intent)
            .getCharSequence(KeysConstants.KEY_REPLAY_NOTIFICATION)

        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            action = intent.action
            putExtra(ExtraConstants.EXTRA_TOAST_TEXT,userInputText)
        }
        context.startActivity(activityIntent)
    }
}