package com.lucas.historygreatests.repositories

import androidx.annotation.WorkerThread
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.database.daos.ChaptersDao
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.repositories.interfaces.IChapterRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class ChapterRepository(private val chaptersDao: ChaptersDao) : IChapterRepository {

    override val firestoreService = FirestoreChaptersService()

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    override val allChapters: Flow<List<Chapter>> = chaptersDao.getChapters()

    override suspend fun getLastDocumentId() = chaptersDao.getLasChapterId()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertList(chapters: List<Chapter>, refresh: Boolean) {
        if (refresh) chaptersDao.deleteAll()
        chaptersDao.insertList(chapters)
    }

    override suspend fun loadChaptersFromRemote(
        bookId: String,
        callback: FirestorePaginationQueryCallback<Chapter>,
        lastDocumentSnapshot: DocumentSnapshot?
    ) {
        val lastDocumentId = getLastDocumentId()
        if (lastDocumentSnapshot != null || lastDocumentId.isNullOrEmpty()) {
            firestoreService.getChaptersByBookId(bookId, lastDocumentSnapshot, callback)
        } else {
            firestoreService.getChaptersByBookIdWithDocumentId(bookId, lastDocumentId, callback)
        }

    }
}