package com.mofuapps.bgcountdowntimer.domain.session

import java.util.Date

class ResumeSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
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
