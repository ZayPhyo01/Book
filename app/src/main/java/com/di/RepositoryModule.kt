package com.di

import com.data.repository.BookRepository
import org.koin.dsl.module

val bookRepositoryModule = module {
    single {
        BookRepository(
            get()
        )
    }
}