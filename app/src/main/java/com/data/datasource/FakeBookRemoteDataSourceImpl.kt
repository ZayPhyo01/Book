package com.data.datasource

import com.domain.model.BookModel


class FakeBookRemoteDataSourceImpl : BookRemoteDataSource {
    override suspend fun getBookList(): Result<List<BookModel>> {
        return Result.success(
            listOf(
                BookModel(
                    id = "12",
                    name = "Horror",
                    description = "description 123",
                    bookCover = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcjNfD2cn-8h6oiBJGEVvhrCynVhrb6ulu1w&s"
                )
            )
        )
    }
}