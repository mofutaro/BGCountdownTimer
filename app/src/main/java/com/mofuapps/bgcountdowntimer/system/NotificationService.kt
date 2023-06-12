package com.mofuapps.bgcountdowntimer.system

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.mofuapps.bgcountdowntimer.domain.notification.Notification
import com.mofuapps.bgcountdowntimer.domain.notification.Notification.KEY_NOTIFICATION_MESSAGE
import com.mofuapps.bgcountdowntimer.domain.notification.Notification.KEY_REPEAT_NOTIFICATION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotificationService: Service() {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val periodMillis = 2000L
    private var repeat = false
    private lateinit var message: String

    private val wakeLock: PowerManager.WakeLock by lazy {
        (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
            newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "com.mofuapps.bgcountdowntimer:ServiceWakelock")
        }
    }

    private fun acquireWakelock() {
        try {
            wakeLock.let {
                wakeLock.setReferenceCounted(false)
                if (!wakeLock.isHeld) {
                    wakeLock.acquire(10*60*1000L /*10 minutes*/)
                }
            }
        } catch (_: RuntimeException) {
        }
    }

    private fun releaseWakelock() = try {
        wakeLock.let {
            if (it.isHeld) {
                it.release()
            }
        }
    } catch (_: RuntimeException) {
    }

    private suspend fun startNotification() {
        delay(periodMillis)
        while(repeat) {
            makeTimerNotification(message, this)
            delay(periodMillis)
        }
        delay(40000L)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        message = intent?.getStringExtra(KEY_NOTIFICATION_MESSAGE) ?: ""
        repeat = intent?.getBooleanExtra(KEY_REPEAT_NOTIFICATION, false) ?: false
        Log.d("ALARM", "onStartCommand at NotificationService")
        startForeground(
            Notification.ID,
            getNotificationBuilder(message, this)
                .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
                .build()
        )
        scope.launch {
            startNotification()
            Log.d("ALARM", "stopSelf at NotificationService")
            stopSelf()
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        acquireWakelock()
        Log.d("ALARM", "onCreate")
    }

    override fun onDestroy() {
        releaseWakelock()
        Log.d("ALARM", "onDestroy")
        scope.cancel()
        super.onDestroy()
    }
}