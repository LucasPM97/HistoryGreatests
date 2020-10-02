package com.lucas.historygreatests.models

data class Chapter(
    val chapter_id:String,
    val title:String,
    val description:String,
    val imageUrl:String,
    val imageColor:String ="#CDCDCB",
    val body:String = ""
)