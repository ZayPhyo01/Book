package com.data.mapper

import com.data.model.BookResponse
import com.domain.model.BookModel

fun BookResponse.toBookModel(): List<BookModel> = this.data.book.map {
    BookModel(
        id = it.id,
        name = it.name.orEmpty(),
        description = it.description.orEmpty(),
        bookCover = it.bookCover
    )
}

