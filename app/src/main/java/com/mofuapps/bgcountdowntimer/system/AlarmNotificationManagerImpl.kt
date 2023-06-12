package com.mofuapps.bgcountdowntimer.system

import android.content.Context
import android.content.Intent
import android.os.Build
import com.mofuapps.bgcountdowntimer.domain.notification.AlarmNotificationManager
import com.mofuapps.bgcountdowntimer.domain.notification.Notification

class AlarmNotificationManagerImpl(private val context: Context): AlarmNotificationManager {
    private val serviceClass = NotificationService::class.java
    private val serviceIntent = Intent(context, serviceClass)

    override fun startNotification(message: String, repeat: Boolean) {
        serviceIntent.putExtra(Notification.KEY_NOTIFICATION_MESSAGE, message)
        serviceIntent.putExtra(Notification.KEY_REPEAT_NOTIFICATION, repeat)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }

    override fun stopNotification() {
        context.stopService(serviceIntent)
    }
}