package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.domain.model.BookModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException

class ApiException(message: String, val code: Int) : Exception(message)

class BookRemoteDataSource(private val httpClient: HttpClient) {
    suspend fun getBookList(): Result<List<BookModel>> {
        return try {
            val httpResponse = httpClient.get("http://54.179.102.152/api/user/books_simple")
            when (httpResponse.status) {
                HttpStatusCode.OK -> {
                    val bookResponse: BookResponse = httpResponse.body()
                    return Result.success(bookResponse.toBookModel())
                }

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
            return Result.failure(e)
        }
    }
}