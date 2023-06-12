package com.mofuapps.bgcountdowntimer.system

import android.content.Context
import android.content.Intent
import android.os.Build
import com.mofuapps.bgcountdowntimer.domain.notification.Notification.KEY_NOTIFICATION_MESSAGE
import com.mofuapps.bgcountdowntimer.domain.notification.Notification.KEY_REPEAT_NOTIFICATION
import com.mofuapps.bgcountdowntimer.domain.notification.StartNotificationUseCase
import javax.inject.Inject

class StartNotificationUseCaseImpl @Inject constructor(private val context: Context): StartNotificationUseCase {
    override operator fun invoke(message: String, repeat: Boolean) {
        val serviceIntent = Intent(context, NotificationService::class.java)
        serviceIntent.putExtra(KEY_NOTIFICATION_MESSAGE, message)
        serviceIntent.putExtra(KEY_REPEAT_NOTIFICATION, repeat)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}