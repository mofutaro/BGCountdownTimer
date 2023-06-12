package com.mofuapps.bgcountdowntimer.di

import android.content.Context
import com.mofuapps.bgcountdowntimer.domain.alarm.NotifyZeroAlarmManager
import com.mofuapps.bgcountdowntimer.domain.notification.AlarmNotificationManager
import com.mofuapps.bgcountdowntimer.system.AlarmNotificationManagerImpl
import com.mofuapps.bgcountdowntimer.system.NotifyZeroAlarmManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SystemModule {

    @Singleton
    @Provides
    fun provideNotifyZeroAlarmManager(@ApplicationContext context: Context): NotifyZeroAlarmManager {
        return NotifyZeroAlarmManagerImpl(context)
    }

    @Singleton
    @Provides
    fun provideAlarmNotificationManager(@ApplicationContext context: Context): AlarmNotificationManager {
        return AlarmNotificationManagerImpl(context)
    }
}