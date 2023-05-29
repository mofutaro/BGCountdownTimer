package com.mofuapps.bgcountdowntimer.ui.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mofuapps.bgcountdowntimer.domain.session.CancelSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.FinishSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.PauseSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.ResumeSessionUseCase
import com.mofuapps.bgcountdowntimer.domain.session.Session
import com.mofuapps.bgcountdowntimer.domain.session.SessionRepository
import com.mofuapps.bgcountdowntimer.domain.session.SessionState
import com.mofuapps.bgcountdowntimer.domain.session.StartSessionUseCase
import com.mofuapps.bgcountdowntimer.ui.MyApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TimerViewModel(
    sessionRepository: SessionRepository,
    private val startSession: StartSessionUseCase,
    private val pauseSession: PauseSessionUseCase,
    private val resumeSession: ResumeSessionUseCase,
    private val cancelSession: CancelSessionUseCase,
    private val finishSession: FinishSessionUseCase
): ViewModel() {

    private val initialDurationSec = 10

    private val initialUIState = TimerScreenUIState(
        stage = TimerScreenStage.STAND_BY,
        visualIndicator = 1f,
        numericalIndicator = "00:00"
    )

    private fun Session.textProgress(): String {
        val waitingMillis = (durationMillis() - progressMillisAtResumed)
        val waiting = waitingMillis / 1000 + if (waitingMillis%1000>0) 1 else 0
        val hours = (waiting / 3600).toInt()
        val residue = (waiting % 3600).toInt()
        val minutes = residue / 60
        val seconds = residue % 60
        val str: String =  if (waiting >= 3600) {
            "%02d:%02d:%02d".format(hours, minutes, seconds)
        } else {
            "%02d:%02d".format(minutes, seconds)
        }
        return if (str.length <= 8) {
            str
        } else {
            ""
        }
    }

    init {
        val tickFlow: Flow<Session?> = sessionRepository.flow.transformLatest { session: Session? ->
            emit(session)
            if (session != null && session.state == SessionState.RUNNING) {
                while(true) {
                    delay(1000L)
                    emit(session)
                }
            }
        }

        tickFlow.onEach { result: Session? ->
            var stage = initialUIState.stage
            var visualIndicator = initialUIState.visualIndicator
            var numericalIndicator = initialUIState.numericalIndicator
            result?.let { session: Session ->
                stage = when(session.state) {
                    SessionState.RUNNING -> TimerScreenStage.RUNNING
                    SessionState.PAUSED -> TimerScreenStage.PAUSED
                    SessionState.FINISHED -> TimerScreenStage.FINISHED
                }
                visualIndicator = 1f - (session.progressPercent() / 100).toFloat()
                numericalIndicator = session.textProgress()
            }
            _uiState.update {
                it.copy(
                    stage = stage,
                    visualIndicator = visualIndicator,
                    numericalIndicator = numericalIndicator
                )
            }
            if (result != null && result.progressPercent() >= 100) {
                finishSession()
            }
        }.launchIn(viewModelScope)
    }

    private val _uiState = MutableStateFlow (
        initialUIState
    )

    internal val uiState = _uiState.asStateFlow()

    internal fun startTimer() {
        viewModelScope.launch {
            startSession(initialDurationSec)
        }
    }

    internal fun pauseTimer() {
        viewModelScope.launch {
            pauseSession()
        }
    }

    internal fun resumeTimer() {
        viewModelScope.launch {
            resumeSession()
        }
    }

    internal fun cancelTimer() {
        viewModelScope.launch {
            cancelSession()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val appContainer = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication).appContainer
                TimerViewModel(
                    sessionRepository = appContainer.sessionRepository,
                    startSession = appContainer.startSessionUseCase,
                    pauseSession = appContainer.pauseSessionUseCase,
                    resumeSession = appContainer.resumeSessionUseCase,
                    cancelSession = appContainer.cancelSessionUseCase,
                    finishSession = appContainer.finishSessionUseCase
                )
            }
        }
    }
}