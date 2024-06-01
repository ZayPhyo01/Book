package com.data.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.data.db.daos.BookDao
import com.data.db.entities.BookEntity

@Database(
    entities = [
        BookEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}