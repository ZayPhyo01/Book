package com.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class LogRequest(
    val userEmail: String,
    val userDeviceName: String,
    val eventName: String,
    val eventDescription: String,
    val createdAt: String,
    val updatedAt: String
)