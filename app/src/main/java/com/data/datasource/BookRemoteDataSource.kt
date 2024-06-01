package com.data.datasource

import com.domain.model.BookModel

interface BookRemoteDataSource {
    suspend fun getBookList(): Result<List<BookModel>>
}