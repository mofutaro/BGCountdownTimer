package com.mofuapps.bgcountdowntimer.domain.session

import java.util.Date

class StartSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(durationSec: Int) {
        val newSession = Session(
            durationSec = durationSec,
            progressMillisAtResumed = 0,
            resumedAt = Date(),
            state = SessionState.RUNNING
        )
        sessionRepository.insert(newSession)
    }
}