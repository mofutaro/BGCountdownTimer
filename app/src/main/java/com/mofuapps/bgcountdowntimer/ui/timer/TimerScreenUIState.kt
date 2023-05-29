package com.mofuapps.bgcountdowntimer.ui.timer

enum class TimerScreenStage {
    STAND_BY,
    RUNNING,
    PAUSED,
    FINISHED
}

data class TimerScreenUIState(
    val stage: TimerScreenStage,
    val visualIndicator: Float,
    val numericalIndicator: String
)