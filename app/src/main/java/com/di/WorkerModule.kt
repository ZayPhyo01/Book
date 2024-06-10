package com.di

import com.data.workers.UploadLogsWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val workerModule = module {
    worker {
        UploadLogsWorker(
            context = androidContext(),
            workParams = get(),
            logRepository = get()
        )
    }
}