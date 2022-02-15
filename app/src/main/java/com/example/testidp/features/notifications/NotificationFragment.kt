package com.example.testidp.features.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import com.example.testidp.App
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import com.example.testidp.main.MainActivity
import com.example.testidp.utils.constants.ActionConstants.ACTION_SHOW_TOAST
import com.example.testidp.utils.constants.ExtraConstants
import com.example.testidp.utils.constants.KeysConstants

class NotificationFragment : BaseFragment(R.layout.fragment_notifications) {
    companion object {

        private const val ID_NOTIFICATION_TEXT = 101
        private const val ID_NOTIFICATION_PROGRESS = 102

        fun newInstance() = NotificationFragment()
    }

    private var btnText: Button? = null
    private var btnNotificationAction: Button? = null
    private var btnNotificationReplay: Button? = null
    private var btnNotificationProgress: Button? = null
    private var btnNotificationMessaging: Button? = null
    private var btnNotificationBigText: Button? = null
    private var btnNotificationInbox: Button? = null
    private var btnNotificationBigPicture: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        btnText = view.findViewById(R.id.btnNotificationText)
        btnNotificationAction = view.findViewById(R.id.btnNotificationAction)
        btnNotificationReplay = view.findViewById(R.id.btnNotificationReplay)
        btnNotificationProgress = view.findViewById(R.id.btnNotificationProgress)
        btnNotificationMessaging = view.findViewById(R.id.btnNotificationMessaging)
        btnNotificationBigText = view.findViewById(R.id.btnNotificationBigText)
        btnNotificationInbox = view.findViewById(R.id.btnNotificationInbox)
        btnNotificationBigPicture = view.findViewById(R.id.btnNotificationBigPicture)

        btnText?.setOnClickListener {
            showSimpleTextNotification()
        }

        btnNotificationAction?.setOnClickListener {
            showNotificationWithAction()
        }

        btnNotificationReplay?.setOnClickListener {
            showReplayNotification()
        }

        btnNotificationProgress?.setOnClickListener {
            showProgressNotification()
        }

        btnNotificationMessaging?.setOnClickListener {
            showMessagingStyledNotification()
        }

        btnNotificationBigText?.setOnClickListener {
            showBitText()
        }

        btnNotificationInbox?.setOnClickListener {
            showInboxNotification()
        }

        btnNotificationBigPicture?.setOnClickListener {
            showBigPictureNotification()
        }
    }

    private fun showSimpleTextNotification() {
        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_TEXT)
            .setContentTitle("This is a title")
            .setContentText("This is a content text")
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setContentIntent(createPendingIntent())
            .build()
        showNotification(notification)
    }

    private fun showNotificationWithAction() {
        val actionIntent =
            Intent(requireContext(), NotificationsActionBroadcastReceiver::class.java).apply {
                action = ACTION_SHOW_TOAST
                putExtra(ExtraConstants.EXTRA_TOAST_TEXT, "This is the notification action text")
            }

        val actionPendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            actionIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_TEXT)
            .setContentText("Title action")
            .setContentText("Action description")
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setContentIntent(createPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(R.drawable.ic_baseline_outlet_24, "JSUT DO it!", actionPendingIntent)
            .build()
        showNotification(notification)

    }

    private fun showReplayNotification() {
        val remoteInput = RemoteInput.Builder(KeysConstants.KEY_REPLAY_NOTIFICATION)
            .setLabel("Replay")
            .build()

        val replayIntent =
            Intent(requireContext(), NotificationReplayBroadcastReceiver::class.java).apply {
                action = ACTION_SHOW_TOAST
            }


        val replayPendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            123,
            replayIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        val action = NotificationCompat
            .Action
            .Builder(R.drawable.ic_send_24, "Replay", replayPendingIntent)
            .addRemoteInput(remoteInput)
            .build()

        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_TEXT)
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setContentTitle("This is the replay title")
            .setContentText("This is the content of the replay notification")
            .addAction(action)
            .build()

        showNotification(notification)
    }

    private fun showProgressNotification() {
        val builder = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_LOADING)
            .setContentTitle("Downloading something")
            .setContentText("Download in progress")
            .setSmallIcon(R.drawable.ic_download_24)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOnlyAlertOnce(true)

        val max = 100
        var current = 0

        val notificationManager = NotificationManagerCompat.from(requireContext()).apply {
            builder.setProgress(max, current, false)
//            show progress without percent
//            builder.setProgress(0, 0, true)
            notify(ID_NOTIFICATION_PROGRESS, builder.build())
        }

        val thread = object : Thread() {
            override fun run() {
                try {
                    while (!currentThread().isInterrupted) {
                        current += 20
                        builder.setProgress(max, current, false)
                        builder.setSound(null)
                        notificationManager.notify(ID_NOTIFICATION_PROGRESS, builder.build())
                        if (current == max) {
                            builder.setProgress(0, 0, false)
                            builder.setContentText("Download complete")
                            builder.setOnlyAlertOnce(false)

                            notificationManager.notify(ID_NOTIFICATION_PROGRESS, builder.build())
                            interrupt()
                        }
                        sleep(1000)
                    }
                } catch (ex: InterruptedException) {
                    ex.message
                }
            }
        }
        thread.start()
    }

    private fun showMessagingStyledNotification() {
        val personMe = Person.Builder()
            .setBot(false)
            .setIcon(
                IconCompat.createWithResource(
                    requireContext(),
                    R.drawable.ic_person_24
                )
            )
            .setName("Me")
            .build()

        val personTwo = Person.Builder()
            .setBot(false)
            .setIcon(
                IconCompat.createWithResource(
                    requireContext(),
                    R.drawable.ic_person_plus_24
                )
            )
            .setName("Person Two")
            .build()
        val personThree = Person.Builder()
            .setBot(false)
            .setIcon(
                IconCompat.createWithResource(
                    requireContext(),
                    R.drawable.ic_person_minus_24
                )
            )
            .setName("Person Three")
            .build()
        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_STYLED)
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setStyle(
                NotificationCompat.MessagingStyle(personMe)
                    .setConversationTitle("I hate you ALL111")
                    .addMessage("I hate you!", 123L, personMe)
                    .addMessage("I hate you tooooo!!!", 124L, personTwo)
                    .addMessage("I hate you both, jerk asses", 125L, personThree)
            ).build()
        showNotification(notification)
    }

    private fun showBitText() {
        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_STYLED)
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setContentTitle("Big text")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "Much longer text that cannot fit one line... 11111111111111111111111" +
                                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        showNotification(notification)
    }

    private fun showInboxNotification() {
        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_STYLED)
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setContentTitle("Inbox styled")
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("The line 1")
                    .addLine("The line 2")
                    .addLine("The line 3")
                    .addLine("The line 4")
                    .addLine("The line 5")
                    .addLine("The line 6")
                    .addLine("The line 7")
                    .addLine("The line 8")
                    .addLine("The line 9")
                    .addLine("The line 10")
                    .setBigContentTitle("Big content title")
                    .setSummaryText("Summary text")
            )
            .build()
        showNotification(notification)
    }

    private fun showBigPictureNotification() {
        val bigIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_run_circle_60)
        val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.retr)

        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_STYLED)
            .setSmallIcon(R.drawable.ic_smoking_24)
            .setLargeIcon(bigIcon)
            .setContentTitle("Big picture")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bigPicture)
            )
            .build()
        showNotification(notification)
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        return PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun showNotification(notification: Notification) {
        NotificationManagerCompat.from(requireContext()).notify(ID_NOTIFICATION_TEXT, notification)
    }
}