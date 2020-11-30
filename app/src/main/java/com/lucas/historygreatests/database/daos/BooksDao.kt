package com.lucas.historygreatests.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.historygreatests.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM books_table WHERE topicId =:topicId ORDER BY views DESC")
    fun getBooksByTopicId(topicId: String): Flow<List<Book>>

    @Query("SELECT book_id FROM books_table WHERE topicId =:topicId ORDER BY views ASC LIMIT 1")
    suspend fun getLasBookId(topicId: String): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(books: List<Book>)

    @Query("DELETE FROM books_table")
    suspend fun deleteAll()
}