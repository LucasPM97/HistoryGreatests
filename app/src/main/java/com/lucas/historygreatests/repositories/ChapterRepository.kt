package com.lucas.historygreatests.repositories

import androidx.annotation.WorkerThread
import com.lucas.historygreatests.database.daos.BooksDao
import com.lucas.historygreatests.database.daos.ChaptersDao
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ChapterRepository(private val chaptersDao: ChaptersDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allChapters: Flow<List<Chapter>> = chaptersDao.getChapters()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(chapter: Chapter) {
        chaptersDao.insert(chapter)
    }
}