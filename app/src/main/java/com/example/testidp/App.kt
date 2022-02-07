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
        val channels = listOfNotNull(createTextNotificationChannel())
        channels.forEach { channel ->
            val manager = getNotificationsManager()
            manager.createNotificationChannel(channel)
        }
    }

    private fun createTextNotificationChannel(): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Text Notifications"
            val description = "This chennel provides the simple text notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH
            NotificationChannel(
                CHANNEL_ID_TEXT,
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