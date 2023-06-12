package com.mofuapps.bgcountdowntimer.domain.notification

interface AlarmNotificationManager {
    fun startNotification(message: String, repeat: Boolean)

    fun stopNotification()
}