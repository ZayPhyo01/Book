package com.data.datasource

import com.data.mapper.toBookModel
import com.data.model.BookResponse
import com.data.utils.handle
import com.domain.model.BookModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class BookRemoteDataSourceImpl(private val httpClient: HttpClient) : BookRemoteDataSource {
    override suspend fun getBookList(): Result<List<BookModel>> {
        return handle<BookResponse> { httpClient.get("http://54.179.102.152/api/user/books_simple") }
            .map {
                it.toBookModel()
            }
    }


}

// get -> BookResponse -> .handle -> Result<BookResponse> -> success or fail
// map

// viewmodel depends repo depends datasource depends ktor
// outer             inner        outer              outer
// DIP -> High level module should not depends on low level module
// both should depends on abstraction

// Repo ---> datasource -> datasource
// testing