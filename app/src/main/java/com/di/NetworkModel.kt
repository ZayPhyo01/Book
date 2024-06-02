package com.di

import com.data.service.KtorUtils
import org.koin.dsl.module

// ktor / retrofit , json / volley

val networkModule = module {
    single {
        KtorUtils().createKtor(
            appContext = get(),
            authLocalDataSource = get()
        )
    }
}