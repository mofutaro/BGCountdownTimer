package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PauseSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val currentSession = sessionRepository.find()
        currentSession?.let {
            val updatedSession = it.copy(
                progressMillisAtResumed = it.currentProgressMillis(),
                state = SessionState.PAUSED
            )
            sessionRepository.update(updatedSession)
        }
    }
}