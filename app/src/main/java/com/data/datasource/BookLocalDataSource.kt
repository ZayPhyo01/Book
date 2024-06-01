package com.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.data.db.daos.BookDao
import com.data.mapper.toBookModel
import com.data.mapper.toEntity
import com.domain.model.BookModel

class BookLocalDataSource(
    private val bookDao: BookDao
) {

    suspend fun save(books: List<BookModel>) {
        bookDao
            .insertAll(books.map { it.toEntity() })
    }

    fun getAllBooks(): LiveData<List<BookModel>> = bookDao
        .getAll()
        .map { list -> list.map { it.toBookModel() } }
}