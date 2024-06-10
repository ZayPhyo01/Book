package com.data.datasource

import com.domain.model.LogModel

interface LogLocalDataSource {
    suspend fun getLogs(): List<LogModel>
    suspend fun saveLog(log: LogModel)
    suspend fun deleteLog(log: LogModel)
    suspend fun updateServerId(id: Long, serverId: String)
}