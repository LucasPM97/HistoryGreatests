package com.lucas.historygreatests.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.historygreatests.models.Topic
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicsDao {

    @Query("SELECT * FROM topics_table ORDER BY views DESC")
    fun getTopics() : Flow<List<Topic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(topic: Topic)

    @Query("DELETE FROM topics_table")
    suspend fun deleteAll()
}