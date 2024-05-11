package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.domain.model.BookModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BookRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun getBookList(): List<BookModel> {
        val httpResponse = httpClient.get("http://54.179.102.152/api/user/books_simple")
        val bookResponse: BookResponse = httpResponse.body()
        return bookResponse.toBookModel()
    }
}