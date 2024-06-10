package com.domain.model

data class LogModel(
    val id: Long?,
    val userEmail: String,
    val userDeviceName: String,
    val eventName: String,
    val eventDescription: String,
    val createdAt: String,
    val updatedAt: String,
    var serverId: String?
)
