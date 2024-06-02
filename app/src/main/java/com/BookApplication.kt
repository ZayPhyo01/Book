package com

import android.app.Application
import com.di.authLocalDatasourceModule
import com.di.authRemoteDataSourceModule
import com.di.authRepositoryModule
import com.di.bookRemoteDataSourceModule
import com.di.bookRepositoryModule
import com.di.bookViewModelModule
import com.di.loginViewModelModule
import com.di.networkModule
import com.di.splashViewModelModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        startKoin {
            androidContext(this@BookApplication)
            modules(
                bookViewModelModule,
                splashViewModelModule,
                loginViewModelModule,
                bookRepositoryModule,
                bookRemoteDataSourceModule,
                authRepositoryModule,
                authRemoteDataSourceModule,
                authLocalDatasourceModule,
                networkModule
            )
        }
    }
}