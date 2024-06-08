package com.data.utils

import com.data.exceptions.ApiException
import com.data.model.LoginResponse
import com.data.model.requests.LoginRequest
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <reified T> handle(
    crossinline apiCall: suspend () -> HttpResponse
): Result<T> {
    return try {
        val httpResponse = apiCall()
        when (httpResponse.status) {
            //200
            HttpStatusCode.OK -> {
                //Success?
                val response: T = httpResponse.body()
                if (response != null) {
                    return Result.success(response)
                }

                return Result.failure(
                    ApiException(
                        code = httpResponse.status.value,
                        message = "Something went wrong"
                    )
                )
                //Fail or Success?

            }

            //404
            HttpStatusCode.NotFound -> Result.failure(
                ApiException(
                    code = httpResponse.status.value,
                    message = "NEW_USER"
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
        //Fail or Success?
        return Result.failure(e)
    }
}
