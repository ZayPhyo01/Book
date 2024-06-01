package com.di

import com.data.repository.AuthRepository
import com.data.repository.BookRepository
import org.koin.dsl.module

val bookRepositoryModule = module {
    single {
        BookRepository(
            get(),
            get()
        )
    }

    single {
        AuthRepository(
            get()
        )
    }
}