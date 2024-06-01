package com.di

import com.data.datasource.AuthRemoteDataSource
import com.data.datasource.AuthRemoteDataSourceImpl
import com.data.datasource.BookRemoteDataSource
import com.data.datasource.BookRemoteDataSourceImpl
import com.data.datasource.FakeBookRemoteDataSourceImpl
import org.koin.dsl.module

val bookRemoteDataSourceImplModule = module {
    single {
        FakeBookRemoteDataSourceImpl() as BookRemoteDataSource
    }
    single {
        AuthRemoteDataSourceImpl(
            get()
        ) as AuthRemoteDataSource
    }
}