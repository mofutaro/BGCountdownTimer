package com.mofuapps.bgcountdowntimer.data.session.impl

import com.mofuapps.bgcountdowntimer.data.session.Session
import com.mofuapps.bgcountdowntimer.data.session.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StubSessionRepository(): SessionRepository {
    override val flow: Flow<Session?>
        get() = flow {}

    override suspend fun find(): Session? { return null }
    override suspend fun insert(session: Session) {}
    override suspend fun update(session: Session) {}
    override suspend fun delete(session: Session) {}
}
