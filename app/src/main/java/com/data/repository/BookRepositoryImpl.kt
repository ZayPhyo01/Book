package com.data.repository

import com.data.datasource.BookRemoteDataSource
import com.data.datasource.BookRemoteDataSourceImpl
import com.data.datasource.FakeBookRemoteDataSourceImpl
import com.domain.model.BookModel

class BookRepositoryImpl constructor(private val bookRemoteDataSource: BookRemoteDataSource) :
    BookRepository {

    override suspend fun getBookList(): Result<List<BookModel>> {
        return bookRemoteDataSource.getBookList()
    }
}