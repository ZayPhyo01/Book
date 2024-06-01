package com.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.data.datasource.BookLocalDataSource
import com.data.datasource.BookRemoteDataSource
import com.domain.model.BookModel

sealed class DomainError(override val message: String) : Exception(message) {
    data class BookDomainError(val errorMessage: String) : DomainError(errorMessage)
}

class BookDomainError(override val message: String) : Exception(message)

class BookRepository constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) {

    private val _bookList: LiveData<List<BookModel>> = bookLocalDataSource.getAllBooks()
    val bookListLD: LiveData<List<BookModel>> = _bookList

    suspend fun getBookList() {
        bookRemoteDataSource
            .getBookList()
            .fold(
                onSuccess = {
                    bookLocalDataSource
                        .save(it)
                },
                onFailure = {
                    throw BookDomainError(it.localizedMessage ?: "Something went wrong")
                }
            )
    }
}