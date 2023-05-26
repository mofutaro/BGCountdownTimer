package com.mofuapps.bgcountdowntimer.data.session.impl

import com.mofuapps.bgcountdowntimer.data.session.Session
import com.mofuapps.bgcountdowntimer.data.session.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StubSessionRepository(): SessionRepository {
    override val flow: Flow<Session?>
        get() = flow {}
    override fun insert(session: Session) {}
    override fun update(session: Session) {}
    override fun delete(session: Session) {}
}
