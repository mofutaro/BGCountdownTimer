package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class StartSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(durationSec: Int) = withContext(Dispatchers.IO) {
        val newSession = Session(
            durationSec = durationSec,
            progressMillisAtResumed = 0,
            resumedAt = Date(),
            state = SessionState.RUNNING
        )
        sessionRepository.insert(newSession)
    }
}