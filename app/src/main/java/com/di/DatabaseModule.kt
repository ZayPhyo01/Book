package com.di

import android.content.Context
import androidx.room.Room
import com.data.db.AppDatabase
import com.data.db.daos.BookDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DbName = "RoomDbName"
val dbModule = module {
    single { provideRoomDatabase(androidContext()) }
    single { provideBookDao(get()) }
}

fun provideRoomDatabase(context: Context): AppDatabase {
    val database: AppDatabase?
    database = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .build()
    return database
}

private fun provideBookDao(appDatabase: AppDatabase): BookDao {
    return appDatabase.bookDao()
}