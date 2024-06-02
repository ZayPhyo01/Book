package com.data.utils

import androidx.lifecycle.LiveData
import com.util.SingleLiveEvent

sealed class GlobalEvents {
    data object Unauthorized : GlobalEvents()
}

object GlobalEventManager {

    private val _globalEvent = SingleLiveEvent<GlobalEvents>()

    val globalEvents: LiveData<GlobalEvents> = _globalEvent

    fun sendUnauthorizedEvent() {
        _globalEvent.postValue(GlobalEvents.Unauthorized)
    }
}