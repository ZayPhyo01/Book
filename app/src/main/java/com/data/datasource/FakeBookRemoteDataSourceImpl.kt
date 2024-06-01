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
                    bookCover = ""
                )
            )
        )
    }
}