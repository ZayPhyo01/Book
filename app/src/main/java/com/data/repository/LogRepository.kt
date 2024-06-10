package com.data.repository

import com.domain.model.LogModel

interface LogRepository {
    suspend fun uploadLog(eventName: String, eventDescription: String) : Result<String>
    suspend fun saveLogLocal(eventName: String, eventDescription: String)
    suspend fun deleteLog(log: LogModel)
    suspend fun getLogsToSync(): List<LogModel>
}