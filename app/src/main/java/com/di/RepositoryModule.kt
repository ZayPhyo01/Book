package com.di

import com.data.repository.AuthRepository
import com.data.repository.AuthRepositoryImpl
import com.data.repository.BookRepository
import com.data.repository.BookRepositoryImpl
import org.koin.dsl.module

val bookRepositoryImplModule = module {
    single {
        BookRepositoryImpl(
            get()
        ) as BookRepository
    }

    single {
        AuthRepositoryImpl(
           authRemoteDataSource = get(),
           authLocalDataSource = get()
        ) as AuthRepository
    }
}