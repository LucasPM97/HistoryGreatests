package com.lucas.historygreatests.models

import java.time.LocalDate

data class Book(
    val book_id:String,
    val name:String,
    val imageUrl:String,
    val startYear: String,
    val endYear: String,
)