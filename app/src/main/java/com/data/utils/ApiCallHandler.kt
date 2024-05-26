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

suspend inline fun <reified T> HttpResponse.handle(): Result<T> {
    return try {
        when (this.status) {
            //200
            HttpStatusCode.OK -> {
                //Success?
                val response: T = this.body()
                if (response != null) {
                    return Result.success(response)
                }
                //Fail or Success?
                return Result.failure(
                    ApiException(
                        code = this.status.value,
                        message = "Something went wrong"
                    )
                )
            }

            //404
            HttpStatusCode.NotFound -> Result.failure(
                ApiException(
                    code = this.status.value,
                    message = "NEW_USER"
                )
            )

            else -> Result.failure(
                ApiException(
                    code = this.status.value,
                    message = "Something went wrong"
                )
            )
        }
    } catch (e: Exception) {
        //Fail or Success?
        return Result.failure(e)
    }
}