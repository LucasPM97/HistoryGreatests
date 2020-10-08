package com.lucas.historygreatests.models

import java.time.LocalDate

data class Chapter(
    val chapter_id:String,
    val title:String,
    val description:String?,
    val imageUrl:String,
    val imageColor:String,
    val body:String = "",
    val startYear: String,
    val endYear: String?,
)