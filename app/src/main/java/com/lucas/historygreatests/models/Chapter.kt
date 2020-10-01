package com.lucas.historygreatests.models

data class Chapter(
    val chapter_id:String,
    val title:String,
    val imageUrl:String,
    val description:String = "",
    val body:String = ""
)