package com.lucas.historygreatests.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics_table")
class Topic(
    @PrimaryKey
    val topic_id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val views: Int = 0
)