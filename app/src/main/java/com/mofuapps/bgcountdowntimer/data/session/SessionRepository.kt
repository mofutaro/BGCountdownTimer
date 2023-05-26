package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val flow: Flow<Session?>

    fun insert(session: Session)

    fun update(session: Session)

    fun delete(session: Session)
}