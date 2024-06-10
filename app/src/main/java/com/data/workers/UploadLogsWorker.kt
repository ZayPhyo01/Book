package com.data.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.data.repository.LogRepository

class UploadLogsWorker(
    context: Context,
    private val workParams: WorkerParameters,
    private val logRepository: LogRepository
) : CoroutineWorker(context, workParams) {

    override suspend fun doWork(): Result {
        Log.d("WORK_MANAGER", "Job Started")
        //Get Logs to Sync
        val logs = logRepository.getLogsToSync()
        Log.d("WORK_MANAGER", logs.toString())
        //Upload logs
        logs.forEach {
            val serverId = logRepository.uploadLog(
                eventName = it.eventName,
                eventDescription = it.eventDescription
            ).getOrNull()

            Log.d("WORK_MANAGER", "$serverId synced")

            serverId?.let { serverId ->
                //Delete from local
                logRepository.deleteLog(it)
                Log.d("WORK_MANAGER", "$serverId Deleted")
            }
        }

        return Result.success()

    }
}