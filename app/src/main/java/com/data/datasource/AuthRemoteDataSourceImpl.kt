package com.data.datasource

import com.data.model.requests.LoginRequest
import com.data.utils.handle
import io.ktor.client.HttpClient

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class AuthRemoteDataSourceImpl(private val httpClient: HttpClient) : AuthRemoteDataSource {

    //Result
    // Success -> String -> Access_Token
    // Failed -> Failiure -> Exception -> ViewModel
    // only one
    override suspend fun login(username: String, password: String): Result<String> {

        return handle {
            httpClient
                .post("http://54.179.102.152/api/auth/login") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        LoginRequest(
                            username = username,
                            password = password
                        )
                    )
                }
        }
    }
}
