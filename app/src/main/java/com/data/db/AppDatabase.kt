package com.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.Entity
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.data.db.daos.BookDao
import com.data.db.entities.BookEntity

@Database(
    entities = [
        BookEntity::class
    ],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AppDatabase.MigrationV2::class),
        AutoMigration(from = 2, to = 3, spec = AppDatabase.MigrationRenamePriceFinal::class),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5, spec = AppDatabase.MigrationV5::class)

    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    @RenameColumn(tableName = "m_books", fromColumnName = "price_some", toColumnName = "price_four")
    class MigrationV2 : AutoMigrationSpec

    //
    @RenameColumn(
        tableName = "m_books",
        fromColumnName = "price_four",
        toColumnName = "price_final"
    )
    class MigrationRenamePriceFinal : AutoMigrationSpec


    @DeleteColumn(tableName = "m_books", columnName = "grand_total")
    @RenameColumn(
        tableName = "m_books",
        fromColumnName = "price_final",
        toColumnName = "price_net"
    )
    class MigrationV5 : AutoMigrationSpec


}