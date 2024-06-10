package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.model.LogResponse
import com.data.model.requests.LogRequest
import com.data.model.requests.LoginRequest
import com.data.utils.handle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LogRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : LogRemoteDataSource {

    override suspend fun uploadLog(log: LogRequest): Result<String> {
        return handle<LogResponse> {
            httpClient
                .post("https://sixty-walls-post.loca.lt/api/log")
                {
                    contentType(ContentType.Application.Json)
                    setBody(
                        log
                    )
                }
        }
            .map {
                it.data?.serverId ?: ""
            }
    }
}