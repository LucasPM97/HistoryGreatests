package com.lucas.historygreatests.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lucas.historygreatests.models.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM books_table ORDER BY views ASC")
    fun getBooks() : Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM books_table")
    suspend fun deleteAll()
}