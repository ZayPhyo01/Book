package com.data.datasource

import android.util.Log
import com.data.exceptions.ApiException
import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.model.LoginResponse
import com.data.model.requests.LoginRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import java.lang.NumberFormatException

class AuthRemoteDataSource(private val httpClient: HttpClient) {

    //Result
    // Success -> String -> Access_Token
    // Failed -> Failiure -> Exception -> ViewModel
    // only one
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
                //200
                HttpStatusCode.OK -> {
                    //Success?
                    val loginResponse: LoginResponse = httpResponse.body()
                    loginResponse.data?.accessToken?.let {
                        //save to sharedPref
                        //AuthRemoteDataSource?
                        return Result.success(it)
                    }
                    //Fail or Success?
                    return Result.failure(
                        ApiException(
                            code = httpResponse.status.value,
                            message = "Something went wrong"
                        )
                    )
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
}