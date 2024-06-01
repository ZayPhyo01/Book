package com.domain.model

import com.data.model.BookResponse

data class BookModel(
    val id: String,
    val name: String,
    val description: String,
    val bookCover: String,
    val author: BookResponse.Author?,
    val category: BookResponse.Category?
)
