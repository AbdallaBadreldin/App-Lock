package com.jetawy.applock.ui.services

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.jetawy.applock.R
import com.jetawy.applock.ui.MainActivity

class AppService: Service() {
    private val CHANNEL_ID = "MyForegroundServiceChannel"
    private val NOTIFICATION_ID = 56

    override fun onBind(intent: Intent?): IBinder? {
        return null // Not a bound service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = buildNotification()
        this.startForeground(NOTIFICATION_ID, notification)

        // Start your background work here

        return START_STICKY // Restart service if killed by the system
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "My Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun buildNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java) // Replace with your activity
        val pendingIntent =PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE // Use appropriate flags for your use case
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Foreground Service")
            .setContentText("Service is running in the foreground")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your icon
            .setContentIntent(pendingIntent)
            .build()
    }
}