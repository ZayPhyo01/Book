package com.data.db.typeconverters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DateConverter {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    @TypeConverter
    fun fromString(value: String?): Date? {
        return value?.let {
            dateFormat.parse(it)
        }
    }

    @TypeConverter
    fun dateToString(date: Date?): String? {
        return date?.let {
            dateFormat.format(it)
        }
    }
}