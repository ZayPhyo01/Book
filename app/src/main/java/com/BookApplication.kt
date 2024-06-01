package com

import android.app.Application
import com.di.bookRemoteDataSourceModule
import com.di.bookRepositoryModule
import com.di.bookViewModelModule
import com.di.dbModule
import com.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                dbModule,
                bookViewModelModule,
                bookRepositoryModule,
                bookRemoteDataSourceModule,
                networkModule
            )
            androidContext(this@BookApplication)
        }
    }
}