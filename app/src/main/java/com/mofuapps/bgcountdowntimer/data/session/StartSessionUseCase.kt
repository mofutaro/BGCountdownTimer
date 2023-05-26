package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StartSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(durationSec: Int) = withContext(Dispatchers.IO) {
        val newSession = Session(
            durationSec = durationSec,
            progressMillisAtResumed = 0,
            resumedAt = null,
            state = SessionState.RUNNING
        )
        sessionRepository.insert(newSession)
    }
}