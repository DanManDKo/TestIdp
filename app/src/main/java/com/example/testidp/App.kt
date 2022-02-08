package com.example.testidp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.testidp.utils.getNotificationsManager
import koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        const val CHANNEL_ID_TEXT = "ChannelText"
        const val CHANNEL_ID_LOADING = "Loading"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationsChannels()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(viewModelModule))
        }
    }

    private fun createNotificationsChannels() {
        val channels = listOfNotNull(
            createNotificationChannel(
                name = "Text Notifications",
                description = "This channel provides the simple text notifications",
                channelId = CHANNEL_ID_TEXT
            ),
            createNotificationChannel(
                name = "Loading Notifications",
                description = "This channel provides loading status notifications",
                channelId = CHANNEL_ID_LOADING
            )
//                .apply { this?.setSound(null, null) }
        )
        channels.forEach { channel ->
            val manager = getNotificationsManager()
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotificationChannel(
        name: String,
        description: String,
        channelId: String
    ): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            NotificationChannel(
                channelId,
                name,
                importance
            ).apply {
                setDescription(description)
            }
        } else {
            null
        }
    }

}