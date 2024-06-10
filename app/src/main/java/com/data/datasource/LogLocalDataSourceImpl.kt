package com.data.datasource

import com.data.db.dao.LogDao
import com.data.mapper.toEntity
import com.data.mapper.toModel
import com.domain.model.LogModel

class LogLocalDataSourceImpl(
    private val logDao: LogDao
) : LogLocalDataSource {
    override suspend fun getLogs(): List<LogModel> = logDao.getLogs().map { it.toModel() }

    override suspend fun saveLog(log: LogModel) {
        logDao.saveLog(log = log.toEntity())
    }

    override suspend fun deleteLog(log: LogModel) {
        logDao.deleteLog(log = log.toEntity())
    }

    override suspend fun updateServerId(id: Long, serverId: String) {
        logDao.updateServerId(
            id = id,
            serverId = serverId
        )
    }
}