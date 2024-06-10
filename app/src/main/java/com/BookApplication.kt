package com

import android.app.Application
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.data.workers.UploadLogsWorker
import com.di.bookLocalDataSourceImplModule
import com.di.bookRemoteDataSourceImplModule
import com.di.bookRepositoryImplModule
import com.di.bookViewModelModule
import com.di.dbModule
import com.di.networkModule
import com.di.workerModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        startKoin {
            modules(
                workerModule,
                dbModule,
                bookViewModelModule,
                bookRepositoryImplModule,
                bookRemoteDataSourceImplModule,
                bookLocalDataSourceImplModule,
                networkModule
            )
            androidContext(this@BookApplication)
            workManagerFactory()
        }

        setupWorkManager()
    }

    private fun setupWorkManager() {
        //One Time or Perodic
        // - Build Request
        //Worker Class -> Corountine Worker

        val uploadLogsRequest = PeriodicWorkRequestBuilder<UploadLogsWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).setConstraints(
            Constraints
                .Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "UploadLogsWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                uploadLogsRequest
            )

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uploadLogsRequest.id)
            .observeForever {
                Log.d("JOB", it.state.toString())
            }
    }
}