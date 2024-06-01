package com

import android.app.Application
import com.di.bookRemoteDataSourceImplModule
import com.di.bookRepositoryImplModule
import com.di.bookViewModelModule
import com.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                bookViewModelModule,
                bookRepositoryImplModule,
                bookRemoteDataSourceImplModule,
                networkModule
            )
            androidContext(this@BookApplication)
        }
    }
}