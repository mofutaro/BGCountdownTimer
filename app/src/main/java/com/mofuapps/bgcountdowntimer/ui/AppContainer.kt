package com.mofuapps.bgcountdowntimer.ui

import android.app.Application
import com.mofuapps.bgcountdowntimer.data.getDatabase
import com.mofuapps.bgcountdowntimer.data.session.RealSessionRepository
import com.mofuapps.bgcountdowntimer.domain.session.CancelSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.PauseSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.ResumeSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.StartSessionUseCase

class AppContainer(application: Application) {
    val database = getDatabase(application)
    val sessionRepository = RealSessionRepository(database.sessionDao)
    val startSessionUseCase = StartSessionUseCase(sessionRepository)
    val pauseSessionUseCase = PauseSessionUseCase(sessionRepository)
    val resumeSessionUseCase = ResumeSessionUseCase(sessionRepository)
    val cancelSessionUseCase = CancelSessionUseCase(sessionRepository)
}