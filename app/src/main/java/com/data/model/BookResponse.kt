package com.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* id": "7efc5b4f65e2461ab18784a1c5d3ce2f",
        "name": "Catcher in the Rye",
        "description": "The Catcher in the Rye is a novel by J. D. Salinger, partially published in serial form in 1945–1946 and as a novel in 1951. It was originally intended for adu lts but is often read by adolescents for its theme of angst, alienation and as a critique......",
        "book_cover": "https://images.unsplash.com/photo-1621827979802-6d778e161b28?q=80&w=3000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "author": {
          "id": "665370d429334bdc8e0b2518e544d487",
          "name": "J.D. Salinger",
          "description": "J.D. Salinger was an American writer, best known for his 1951 novel The Catcher in the Rye. Before its publi cation, Salinger published several short stories in Story magazine"
        },
        "category": {
          "id": "e48e4a7fcf284e4aa6dcb909ef7a5ada",
          "category_name": "Novel"
        },
        "rating": 4,
        "price": 150.97
        *
        * */

@Serializable
data class BookResponse(
    val data: Book
) {
    @Serializable
    data class Book(
        @SerialName("books")
        val book: List<BookList>
    )

    @Serializable
    data class BookList(
        val id: String,
        val name: String?,
        val description: String?,
        @SerialName("book_cover")
        val bookCover: String,
        val author: Author?,
        val category: Category?,
        val rating: Double,
        val price: Double
    )

    @Serializable
    data class Category(
        val id: String,
        @SerialName("category_name")
        val categoryName: String
    )

    @Serializable
    data class Author(
        val id: String,
        val name: String,
        val description: String

    )
}


// data { book [] }


