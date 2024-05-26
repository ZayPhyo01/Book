package com.di

import com.data.datasource.AuthRemoteDataSource
import com.data.datasource.BookRemoteDataSource
import org.koin.dsl.module

val bookRemoteDataSourceModule = module {
    single {
        BookRemoteDataSource(
            get()
        )
    }
    single {
        AuthRemoteDataSource(
            get()
        )
    }
}