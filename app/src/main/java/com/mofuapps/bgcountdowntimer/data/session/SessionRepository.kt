package com.mofuapps.bgcountdowntimer.data.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val flow: Flow<Session?>
    suspend fun insert(session: Session)
    suspend fun update(session: Session)
    suspend fun delete(session: Session)
}