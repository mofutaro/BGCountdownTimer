package com.mofuapps.bgcountdowntimer.domain.alarm

interface NotifyZeroAlarmManager {
    fun setAlarm(triggerTime: Long, message: String, repeat: Boolean)

    fun cancelAlarm()
}