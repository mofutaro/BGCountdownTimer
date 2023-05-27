package com.mofuapps.bgcountdowntimer.domain.session


class CancelSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        val currentSession: Session? = sessionRepository.find()
        currentSession?.let {
            sessionRepository.delete(it)
        }
    }
}