package com.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.data.db.dao.LogDao
import com.data.db.dao.UserDao
import com.data.db.entities.LogEntity
import com.data.db.entities.UserEntity
import com.data.db.typeconverters.DateConverter

@Database(
    entities = [
        UserEntity::class,
        LogEntity::class
    ],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.MigrationV3::class),
        AutoMigration(from = 3, to = 4)
    ]
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    @DeleteColumn(tableName = "m_user", columnName = "phone_number_four")
    class MigrationV3 : AutoMigrationSpec

    abstract fun userDao(): UserDao
    abstract fun logDao(): LogDao
}