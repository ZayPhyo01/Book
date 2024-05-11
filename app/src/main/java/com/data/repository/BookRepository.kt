package com.data.repository

import com.data.datasource.BookRemoteDataSource
import com.domain.model.BookModel

class BookRepository constructor(private val bookRemoteDataSource: BookRemoteDataSource) {

    suspend fun getBookList(): List<BookModel> {
        return bookRemoteDataSource.getBookList()
    }
}