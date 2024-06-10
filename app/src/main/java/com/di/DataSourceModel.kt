package com.di

import com.data.datasource.AuthLocalDataSource
import com.data.datasource.AuthLocalDataSourceImpl
import com.data.datasource.AuthRemoteDataSource
import com.data.datasource.AuthRemoteDataSourceImpl
import com.data.datasource.BookRemoteDataSource
import com.data.datasource.BookRemoteDataSourceImpl
import com.data.datasource.FakeBookRemoteDataSourceImpl
import com.data.datasource.LogLocalDataSource
import com.data.datasource.LogLocalDataSourceImpl
import com.data.datasource.LogRemoteDataSource
import com.data.datasource.LogRemoteDataSourceImpl
import com.data.db.AppDatabase
import org.koin.dsl.module
import kotlin.math.sin

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
    single {
        LogRemoteDataSourceImpl(
            get()
        ) as LogRemoteDataSource
    }
}

val bookLocalDataSourceImplModule = module {
    single {
        AuthLocalDataSourceImpl(
            //UserDao
            userDao = get()
        ) as AuthLocalDataSource
    }
    single {
        LogLocalDataSourceImpl(
            logDao = get()
        ) as LogLocalDataSource
    }
}