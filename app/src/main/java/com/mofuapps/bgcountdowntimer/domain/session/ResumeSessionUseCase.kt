package com.mofuapps.bgcountdowntimer.domain.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class ResumeSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val currentSession = sessionRepository.find()
        currentSession?.let {
            val updatedSession = currentSession.copy(
                resumedAt = Date(),
                state = SessionState.RUNNING
            )
            sessionRepository.update(updatedSession)
        }
    }
}
