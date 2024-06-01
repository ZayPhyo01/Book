package com.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.data.model.BookResponse

@Entity(
    tableName = "m_books"
)
data class BookEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val bookCover: String,
    @Embedded(prefix = "auth_")
    val author: BookResponse.Author?,
    @Embedded(prefix = "category_")
    val category: BookResponse.Category?,
    val rating: Double,
    val price: Double
)