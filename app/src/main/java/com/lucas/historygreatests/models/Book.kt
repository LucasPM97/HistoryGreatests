package com.lucas.historygreatests.models

data class Book(
    val book_id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val startYear: String = "",
    val endYear: String = "",
    val views: Int = 0
)