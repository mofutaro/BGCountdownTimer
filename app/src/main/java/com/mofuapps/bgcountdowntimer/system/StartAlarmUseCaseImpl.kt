package com.mofuapps.bgcountdowntimer.system

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.mofuapps.bgcountdowntimer.domain.alarm.Alarm.KEY_NOTIFICATION_MESSAGE
import com.mofuapps.bgcountdowntimer.domain.alarm.Alarm.KEY_REPEAT_NOTIFICATION
import com.mofuapps.bgcountdowntimer.domain.alarm.Alarm.REQUEST_CODE
import com.mofuapps.bgcountdowntimer.domain.alarm.SetAlarmUseCase

class SetAlarmUseCaseImpl(private val context: Context): SetAlarmUseCase {
    private val alarmManager: AlarmManager = context
        .getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override operator fun invoke(triggerTime: Long, message: String, repeat: Boolean) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val bundle = Bundle()
        bundle.putString(KEY_NOTIFICATION_MESSAGE, message)
        bundle.putBoolean(KEY_REPEAT_NOTIFICATION, repeat)
        intent.replaceExtras(bundle)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE + PendingIntent.FLAG_ONE_SHOT + PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("ALARM", "setAlarmClock")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            Log.d("ALARM", "cannotScheduleExactAlarm")
        } else {
            Log.d("ALARM", "canScheduleExactAlarm")
            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(triggerTime, null),
                pendingIntent
            )
        }
    }

}