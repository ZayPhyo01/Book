package com.data.repository

import com.domain.model.BookModel

interface BookRepository {

    suspend fun getBookList(): Result<List<BookModel>>
}