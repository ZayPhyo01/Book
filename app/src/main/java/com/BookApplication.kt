package com

import android.app.Application
import com.di.bookLocalDataSourceImplModule
import com.di.bookRemoteDataSourceImplModule
import com.di.bookRepositoryImplModule
import com.di.bookViewModelModule
import com.di.networkModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        startKoin {
            modules(
                bookViewModelModule,
                bookRepositoryImplModule,
                bookRemoteDataSourceImplModule,
                bookLocalDataSourceImplModule,
                networkModule
            )
            androidContext(this@BookApplication)
        }
    }
}