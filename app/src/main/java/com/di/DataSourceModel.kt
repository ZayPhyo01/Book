package com.di

import com.data.datasource.AuthLocalDatasource
import com.data.datasource.AuthRemoteDataSource
import com.data.datasource.BookRemoteDataSource
import org.koin.dsl.module

val bookRemoteDataSourceModule = module {
    single {
        BookRemoteDataSource(
            get()
        )
    }
}

val authRemoteDataSourceModule = module {
    single {
        AuthRemoteDataSource(
            httpClient = get()
        )
    }
}

val authLocalDatasourceModule = module {
    single {
        AuthLocalDatasource()
    }
}