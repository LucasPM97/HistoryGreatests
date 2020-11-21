package com.lucas.historygreatests.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters_table")
class Chapter(
    @PrimaryKey
    val chapter_id:String="",
    val title:String="",
    val description:String?="",
    val imageUrl:String="",
    val imageColor: String="",
    val body:String = "",
    val startYear: String="",
    val endYear: String?="",
)