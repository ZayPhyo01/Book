package com.data.datasource

import com.data.exceptions.ApiException
import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.model.LoginRequest
import com.data.model.LoginResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun login(username: String, password: String): Result<String> {

        return try {
            val httpResponse = httpClient
                .post("http://54.179.102.152/api/auth/login") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        LoginRequest(
                            username = username,
                            password = password
                        )
                    )
                }
            when (httpResponse.status) {
                HttpStatusCode.OK -> {
                    val loginResponse: LoginResponse = httpResponse.body()
                    loginResponse.data?.accessToken?.let {
                        return Result.success(it)
                    }
                    return Result.failure(
                        ApiException(
                            code = httpResponse.status.value,
                            message = "Something went wrong"
                        )
                    )
                }

                HttpStatusCode.NotFound -> Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "NEW_USER"
                    )
                )

                HttpStatusCode.Unauthorized -> Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "Session Expired"
                    )
                )

                else -> Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "Something went wrong"
                    )
                )
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }


    }
}