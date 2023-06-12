package com.mofuapps.bgcountdowntimer.domain.notification

interface StartNotificationUseCase {
    operator fun invoke(message: String, repeat: Boolean)
}