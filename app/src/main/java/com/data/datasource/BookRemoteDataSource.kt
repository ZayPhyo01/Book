package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.utils.handle
import com.domain.model.BookModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BookRemoteDataSource(private val httpClient: HttpClient) {

    suspend fun getBookList(): Result<List<BookModel>> {
        return handle<BookResponse> { httpClient.get("http://54.179.102.152/api/user/books_simple") }
            .map {
                it.toBookModel()
            }


    }
}

// get -> BookResponse -> .handle -> Result<BookResponse> -> success or fail
// map