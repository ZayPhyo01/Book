package com.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.db.entities.LogEntity

@Dao
interface LogDao {
    @Query("SELECT * FROM t_logs WHERE server_id IS NULL")
    suspend fun getLogs(): List<LogEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLog(log: LogEntity)
    @Delete
    suspend fun deleteLog(log: LogEntity)
    @Query("UPDATE t_logs SET server_id=:serverId WHERE id=:id")
    suspend fun updateServerId(id: Long, serverId: String)
}