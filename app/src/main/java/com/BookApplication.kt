package com

import android.app.Application
import com.di.bookRemoteDataSourceModule
import com.di.bookRepositoryModule
import com.di.bookViewModelModule
import com.di.networkModule
import org.koin.core.context.startKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                bookViewModelModule,
                bookRepositoryModule,
                bookRemoteDataSourceModule,
                networkModule
            )
        }
    }
}