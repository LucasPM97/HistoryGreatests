package com.lucas.historygreatests.database.daos

import androidx.room.*
import com.lucas.historygreatests.models.Chapter
import kotlinx.coroutines.flow.Flow

@Dao
interface ChaptersDao {

    @Query("SELECT * FROM chapters_table WHERE bookId =:bookId ORDER BY `order` ASC")
    fun getBookChapters(bookId: String): Flow<List<Chapter>>

    @Query("SELECT * FROM chapters_table WHERE chapter_id =:chapterId ")
    fun getChapterById(chapterId: String?): Flow<Chapter?>

    @Query("SELECT chapter_id FROM chapters_table ORDER BY `order` ASC LIMIT 1")
    suspend fun getLasChapterId(): String?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(chapters: List<Chapter>)

    @Update
    suspend fun updateChapter(chapter: Chapter)

    @Query("DELETE FROM chapters_table")
    suspend fun deleteAll()
}