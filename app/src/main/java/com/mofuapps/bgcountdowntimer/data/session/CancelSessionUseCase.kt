package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CancelSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() = withContext(Dispatchers.IO) {
        val currentSession: Session? = sessionRepository.find()
        currentSession?.let {
            sessionRepository.delete(it)
        }
    }
}