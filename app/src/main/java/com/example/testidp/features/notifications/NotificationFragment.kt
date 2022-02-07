package com.example.testidp.features.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.testidp.App
import com.example.testidp.R
import com.example.testidp.base.BaseFragment
import com.example.testidp.container.ContainerActivity
import com.example.testidp.main.MainActivity

class NotificationFragment : BaseFragment(R.layout.fragment_notifications) {
    companion object {

        private const val ID_NOTIFICATION_TEXT = 101

        fun newInstance() = NotificationFragment()
    }

    private var btnText: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        btnText = view.findViewById(R.id.btnNotificationText)

        btnText?.setOnClickListener {
            showSimpleTextNotification()
        }
    }

    private fun showSimpleTextNotification() {
        val notification = NotificationCompat.Builder(requireContext(), App.CHANNEL_ID_TEXT)
            .setContentText("This is a title")
            .setContentText("This is a content text")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(createPendingIntent())
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