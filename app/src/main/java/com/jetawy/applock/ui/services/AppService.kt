package com.jetawy.applock.ui.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.jetawy.applock.CHANNEL_ID
import com.jetawy.applock.R
import com.jetawy.applock.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AppService : Service() {
    private val NOTIFICATION_ID = 45581

    override fun onBind(intent: Intent?): IBinder? {
        return null // Not a bound service
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        Log.e("SERVICE", "STARTED STICKY")
        val notification = buildNotification()
        startForeground(NOTIFICATION_ID, notification)
        val endTime = System.currentTimeMillis()
        val startTime = endTime  // Check usage in the last 10 minutes

        var lastTime = System.currentTimeMillis()
        val lastPackage = packageName
        CoroutineScope(Dispatchers.IO).launch {
            while (true)
                try {
                    delay(1000)
                    val usageStatsManager: UsageStatsManager =
                        getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
                    val time = System.currentTimeMillis()
                    val usageStatsList: List<UsageStats> = usageStatsManager.queryUsageStats(
                        UsageStatsManager.INTERVAL_DAILY, time - 1000 * 3600, time
                    )

                    for (usageStats in usageStatsList) {
                        if (usageStats.lastTimeUsed > lastTime && usageStats.packageName != lastPackage) {
                            Log.e(
                                "AppLaunch",
                                "App: " + usageStats.packageName + ", Last Time Used: " + usageStats.lastTimeUsed
                            )
                            lastTime = System.currentTimeMillis()
                        }


                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                    }
                }
        }


        // Start your background work here
        return START_STICKY // Restart service if killed by the system
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT // Set importance to high
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun buildNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_RECEIVER_FOREGROUND
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your icon
//            .setContentIntent(pendingIntent)
            .setChannelId(CHANNEL_ID)
//            .setAutoCancel(true) // Dismiss notification when tapped
            // Add other options like priority, sound, vibration, etc.
            .build()
    }
}