package com.mofuapps.bgcountdowntimer.domain.session

import java.util.Date

class FinishSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        val currentSession: Session? = sessionRepository.find()
        currentSession?.let {
            val updatedSession = it.copy(
                progressMillisAtResumed = it.durationMillis(),
                resumedAt = Date(),
                state = SessionState.FINISHED
            )
            sessionRepository.update(updatedSession)
        }
    }
}