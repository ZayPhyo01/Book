package com.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.data.db.dao.UserDao
import com.data.db.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.MigrationV3::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    @DeleteColumn(tableName = "m_user", columnName = "phone_number_four")
    class MigrationV3 : AutoMigrationSpec

    abstract fun userDao(): UserDao
}