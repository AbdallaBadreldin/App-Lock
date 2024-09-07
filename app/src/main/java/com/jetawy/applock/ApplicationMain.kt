package com.jetawy.applock

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

const val CHANNEL_ID= "FDSAFD_CHANNEL"
open class ApplicationMain: Application() {

    override fun onCreate() {
        super.onCreate()
//    createNotificationChannel()
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

//    private fun  handleStartForeGroundService() {
//        Notification notification =
//        new ForegroundServiceNotification().showNotification(
//            MyApplication.Companion.getMyAppContext().getResources().getString(R.string.audio_call),
//            MyApplication.Companion.getMyAppContext().getResources().getString(R.string.call_is_active_now),
//            CallType.Audio.getType(),
//            MyApplication.Companion.getMyAppContext());
//        if (notification != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                ServiceCompat.startForeground(
//                    this,
//                    FOREGROUND_SERVICE_AUDIO,
//                    notification,
//                    ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE);
//            } else {
//                startForeground(FOREGROUND_SERVICE_AUDIO, notification);
//
//            }}}

        }