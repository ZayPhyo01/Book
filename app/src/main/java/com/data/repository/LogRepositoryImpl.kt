package com.data.repository

import android.os.Build
import com.data.datasource.AuthLocalDataSource
import com.data.datasource.LogLocalDataSource
import com.data.datasource.LogRemoteDataSource
import com.data.model.requests.LogRequest
import com.domain.model.LogModel
import java.util.Date

class LogRepositoryImpl(
    private val logRemoteDataSource: LogRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val logLocalDataSource: LogLocalDataSource
) : LogRepository {
    override suspend fun uploadLog(eventName: String, eventDescription: String): Result<String> {
        val user = authLocalDataSource.getUserInfoOnce()
        return logRemoteDataSource
            .uploadLog(
                log = LogRequest(
                    userDeviceName = "${Build.MANUFACTURER} ${Build.MODEL}",
                    userEmail = user?.email ?: "",
                    eventName = eventName,
                    eventDescription = eventDescription,
                    createdAt = Date().toString(),
                    updatedAt = Date().toString()
                )
            )

    }

    override suspend fun saveLogLocal(eventName: String, eventDescription: String) {
        val user = authLocalDataSource.getUserInfoOnce()
        logLocalDataSource.saveLog(
            log = LogModel(
                id = null,
                userDeviceName = "${Build.MANUFACTURER} ${Build.MODEL}",
                userEmail = user?.email ?: "",
                eventName = eventName,
                eventDescription = eventDescription,
                createdAt = Date().toString(),
                updatedAt = Date().toString(),
                serverId = null
            )
        )
    }

    override suspend fun deleteLog(log: LogModel) {
        logLocalDataSource
            .deleteLog(
                log = log
            )
    }

    override suspend fun getLogsToSync(): List<LogModel> {
        return logLocalDataSource.getLogs()
    }
}