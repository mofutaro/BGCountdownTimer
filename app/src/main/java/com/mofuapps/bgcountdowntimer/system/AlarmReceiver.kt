package com.mofuapps.bgcountdowntimer.system

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mofuapps.bgcountdowntimer.domain.notification.AlarmNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {
    @Inject lateinit var notificationManager: AlarmNotificationManager

    @SuppressLint("ShowToast")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }

        notificationManager.startNotification("A", true)
    }
}