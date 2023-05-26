package com.mofuapps.bgcountdowntimer.data.session

import java.util.Date



data class Session(
    val durationSec: Int,
    val progressMillisAtResumed: Int,
    val resumedAt: Date?,
    val state: SessionState
)
