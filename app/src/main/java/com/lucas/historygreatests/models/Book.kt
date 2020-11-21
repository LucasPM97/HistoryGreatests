package com.lucas.historygreatests.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
class Book(
    @PrimaryKey
    val book_id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val startYear: String = "",
    val endYear: String = "",
    val views: Int = 0
)