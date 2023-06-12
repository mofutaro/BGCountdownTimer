package com.mofuapps.bgcountdowntimer.domain.alarm

interface SetAlarmUseCase {

    operator fun invoke(triggerTime: Long, message: String, repeat: Boolean)
}