package com.mofuapps.bgcountdowntimer.di

import android.content.Context
import com.mofuapps.bgcountdowntimer.domain.alarm.SetAlarmUseCase
import com.mofuapps.bgcountdowntimer.domain.notification.StartNotificationUseCase
import com.mofuapps.bgcountdowntimer.domain.notification.StopNotificationUseCase
import com.mofuapps.bgcountdowntimer.system.SetAlarmUseCaseImpl
import com.mofuapps.bgcountdowntimer.system.StartNotificationUseCaseImpl
import com.mofuapps.bgcountdowntimer.system.StopNotificationUseCaseImpl
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
    fun provideSetAlarmUseCase(@ApplicationContext context: Context): SetAlarmUseCase {
        return SetAlarmUseCaseImpl(context)
    }

    @Singleton
    @Provides
    fun provideStartNotificationUseCase(@ApplicationContext context: Context): StartNotificationUseCase {
        return StartNotificationUseCaseImpl(context)
    }

    @Singleton
    @Provides
    fun provideStopNotificationUseCase(@ApplicationContext context: Context): StopNotificationUseCase {
        return StopNotificationUseCaseImpl(context)
    }
}