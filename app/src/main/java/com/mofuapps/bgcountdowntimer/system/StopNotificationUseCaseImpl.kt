package com.mofuapps.bgcountdowntimer.system

import android.content.Context
import android.content.Intent
import com.mofuapps.bgcountdowntimer.domain.notification.StopNotificationUseCase
import javax.inject.Inject

class StopNotificationUseCaseImpl @Inject constructor(private val context: Context): StopNotificationUseCase {
    override fun invoke() {
        val serviceIntent = Intent(context, NotificationService::class.java)
        context.stopService(serviceIntent)
    }
}