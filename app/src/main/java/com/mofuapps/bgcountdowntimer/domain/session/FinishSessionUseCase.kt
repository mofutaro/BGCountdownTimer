package com.mofuapps.bgcountdowntimer.domain.session

class FinishSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        val currentSession: Session? = sessionRepository.find()
        currentSession?.let {
            val updatedSession = it.copy(
                progressMillisAtResumed = it.durationMillis(),
                state = SessionState.FINISHED
            )
            sessionRepository.update(updatedSession)
        }
    }
}