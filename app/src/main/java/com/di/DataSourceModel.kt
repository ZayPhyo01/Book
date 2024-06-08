package com.di

import com.data.datasource.AuthLocalDataSource
import com.data.datasource.AuthLocalDataSourceImpl
import com.data.datasource.AuthRemoteDataSource
import com.data.datasource.AuthRemoteDataSourceImpl
import com.data.datasource.BookRemoteDataSource
import com.data.datasource.BookRemoteDataSourceImpl
import com.data.datasource.FakeBookRemoteDataSourceImpl
import com.data.db.AppDatabase
import org.koin.dsl.module

val bookRemoteDataSourceImplModule = module {
    single {
        BookRemoteDataSourceImpl(
            httpClient = get()
        ) as BookRemoteDataSource
    }
    single {
        AuthRemoteDataSourceImpl(
            get()
        ) as AuthRemoteDataSource
    }
}

val bookLocalDataSourceImplModule = module {
    single {
        AuthLocalDataSourceImpl(
            //UserDao
            userDao = get()
        ) as AuthLocalDataSource
    }
}