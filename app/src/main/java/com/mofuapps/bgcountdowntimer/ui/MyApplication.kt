package com.mofuapps.bgcountdowntimer.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    val appContainer: AppContainer by lazy { AppContainer(this) }
}