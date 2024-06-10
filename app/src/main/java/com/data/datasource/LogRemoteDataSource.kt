package com.data.datasource

import com.data.model.requests.LogRequest

interface LogRemoteDataSource {
    suspend fun uploadLog(log: LogRequest): Result<String>
}