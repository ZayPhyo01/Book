package com.di

import com.data.datasource.AuthLocalDataSource
import com.data.datasource.AuthLocalDataSourceImpl
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

val bookLocalDataSourceImplModule = module {
    single {
        AuthLocalDataSourceImpl() as AuthLocalDataSource
    }
}