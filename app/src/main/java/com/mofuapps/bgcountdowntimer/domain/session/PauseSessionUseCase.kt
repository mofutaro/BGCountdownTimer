package com.mofuapps.bgcountdowntimer.domain.session


class PauseSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
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