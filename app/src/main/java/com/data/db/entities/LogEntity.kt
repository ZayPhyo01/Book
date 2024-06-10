package com.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "t_logs")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "user_email")
    val userEmail: String,
    @ColumnInfo(name = "user_device_name")
    val userDeviceName: String,
    @ColumnInfo(name = "event_name")
    val eventName: String,
    @ColumnInfo(name = "event_description")
    val eventDescription: String,
    @ColumnInfo(name = "server_id")
    val serverId: String? = null,
    @ColumnInfo(name = "created_at", typeAffinity = ColumnInfo.TEXT)
    val createdAt: Date,
    @ColumnInfo(name = "updated_at", typeAffinity = ColumnInfo.TEXT)
    val updatedAt: Date
)
