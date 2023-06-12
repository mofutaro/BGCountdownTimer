package com.mofuapps.bgcountdowntimer.system

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mofuapps.bgcountdowntimer.domain.notification.StartNotificationUseCase
import com.mofuapps.bgcountdowntimer.domain.notification.StopNotificationUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {
    //private var scope = Job() + Dispatchers.Default
    @Inject lateinit var startNotification: StartNotificationUseCase
    @Inject lateinit var stopNotification: StopNotificationUseCase

    @SuppressLint("ShowToast")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }
        /*val pendingResult = goAsync()
        CoroutineScope(scope).launch {
            try {
                delay(10000L)
                showToast(context, "1111111")
            } finally {
                pendingResult.finish()
            }
        }
        Log.d("TOAST", "end")
        return*/
        startNotification("A", true)
    }

    /*private suspend fun showToast(context: Context, message: String) = withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        Log.d("TOAST", "Show")
    }*/
}