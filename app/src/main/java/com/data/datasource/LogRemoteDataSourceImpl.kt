package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.model.LogResponse
import com.data.model.requests.LogRequest
import com.data.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class LogRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : LogRemoteDataSource {

    override suspend fun uploadLog(log: LogRequest): Result<String> {
        return handle<LogResponse> { httpClient.get("https://dirty-bottles-study.loca.lt/api/log") }
            .map {
                it.data?.serverId ?: ""
            }
    }
}