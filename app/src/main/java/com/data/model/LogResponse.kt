package com.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LogResponse(
    val data: Log?
) {
    @Serializable
    data class Log(
        val serverId: String?
    )
}

