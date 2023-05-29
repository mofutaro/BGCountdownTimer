package com.mofuapps.bgcountdowntimer.data.session

import com.mofuapps.bgcountdowntimer.domain.session.Session
import com.mofuapps.bgcountdowntimer.domain.session.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext

class RealSessionRepository(private val sessionDao: SessionDao): SessionRepository {
    override val flow: Flow<Session?> = sessionDao.findFlow().transform {
        emit(it?.asDomainModel())
    }.flowOn(Dispatchers.IO)

    override suspend fun find(): Session? = withContext(Dispatchers.IO) {
        sessionDao.find()?.asDomainModel()
    }

    override suspend fun insert(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.insert(session.asDatabaseModel())
    }

    override suspend fun update(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.update(session.asDatabaseModel())
    }

    override suspend fun delete(session: Session) = withContext(Dispatchers.IO) {
        sessionDao.delete(session.asDatabaseModel())
    }
}