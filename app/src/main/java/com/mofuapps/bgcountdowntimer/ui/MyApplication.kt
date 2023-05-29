package com.mofuapps.bgcountdowntimer.ui

import android.app.Application

class MyApplication: Application() {
    val appContainer: AppContainer by lazy { AppContainer(this) }
}