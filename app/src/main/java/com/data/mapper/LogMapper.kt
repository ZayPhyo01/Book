package com.data.mapper

import com.data.db.entities.LogEntity
import com.domain.model.LogModel
import java.util.Date

fun LogEntity.toModel() = LogModel(
    id = id,
    userEmail = userEmail,
    userDeviceName = userDeviceName,
    eventName = eventName,
    eventDescription = eventDescription,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString(),
    serverId = serverId
)

fun LogModel.toEntity() = LogEntity(
    id = id,
    userEmail = userEmail,
    userDeviceName = userDeviceName,
    eventName = eventName,
    eventDescription = eventDescription,
    createdAt = Date(),
    updatedAt = Date(),
    serverId = null
)