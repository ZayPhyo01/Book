package com.di

import android.content.Context
import androidx.room.Room
import com.data.db.AppDatabase
import com.data.db.dao.LogDao
import com.data.db.dao.UserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DB_NAME = "book_db"
val dbModule = module {
    //Provide Database
    single { provideRoomDatabase(context = androidContext()) }
    //Provide Dao ->  provideRoomDatabase(context = androidContext())
    single {
        provideUserDao(
            //??AppDatabase ->
            appDatabase = get()
        )
    }
    single { provideLogDao(appDatabase = get()) }

}

private fun provideRoomDatabase(context: Context): AppDatabase {
    val database: AppDatabase?
    //AppDatabase_impl
    database = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DB_NAME
    ).build()
    return database
}

private fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
private fun provideLogDao(appDatabase: AppDatabase): LogDao = appDatabase.logDao()