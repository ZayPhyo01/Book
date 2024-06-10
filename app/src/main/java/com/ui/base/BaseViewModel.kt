package com.ui.base

import androidx.lifecycle.ViewModel
import com.data.repository.LogRepository

abstract class BaseViewModel(
    private val logRepository: LogRepository
) : ViewModel() {

    suspend fun setScreenName(screenName: String) {
        saveLog(
            eventName = "SCREEN_VISIT",
            eventDescription = "VISITED $screenName"
        )
    }

    suspend fun saveLog(
        eventName: String,
        eventDescription: String
    ) {
        logRepository
            .saveLogLocal(
                eventName = eventName,
                eventDescription = eventDescription
            )
    }
}