package com.lucas.historygreatests.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.historygreatests.models.Chapter
import kotlinx.coroutines.flow.Flow

@Dao
interface ChaptersDao {

    @Query("SELECT * FROM chapters_table ORDER BY `order` ASC")
    fun getChapters() : Flow<List<Chapter>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(chapters: List<Chapter>)

    @Query("DELETE FROM chapters_table")
    suspend fun deleteAll()
}