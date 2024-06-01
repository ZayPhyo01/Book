package com.data.mapper

import com.data.db.entities.BookEntity
import com.data.model.BookResponse
import com.domain.model.BookModel

fun BookResponse.toBookModel(): List<BookModel> = this.data.book.map {
    BookModel(
        id = it.id,
        name = it.name.orEmpty(),
        description = it.description.orEmpty(),
        bookCover = it.bookCover,
        category = BookResponse.Category(
            id = it.category?.id.orEmpty(),
            categoryName = it.category?.categoryName.orEmpty()
        ),
        author = BookResponse.Author(
            id = it.author?.id.orEmpty(),
            name = it.author?.description.orEmpty(),
            description = it.author?.description.orEmpty()
        )
    )
}

fun BookModel.toEntity(): BookEntity = BookEntity(
    id = id,
    name = name.orEmpty(),
    description = description.orEmpty(),
    bookCover = bookCover,
    author = author,
    category = category,
    rating = 0.0,
    price = 0.0
)


fun BookEntity.toBookModel(): BookModel = BookModel(
    id = id,
    name = name,
    description = description,
    bookCover = bookCover,
    author = author,
    category = category
)