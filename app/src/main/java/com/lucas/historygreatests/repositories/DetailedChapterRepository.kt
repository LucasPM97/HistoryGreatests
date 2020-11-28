package com.lucas.historygreatests.repositories

import androidx.annotation.WorkerThread
import com.lucas.historygreatests.database.daos.ChaptersDao
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.repositories.interfaces.IDetailedChapterRepository
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class DetailedChapterRepository(private val chaptersDao: ChaptersDao) : IDetailedChapterRepository {

    override val firestoreService = FirestoreChaptersService()

    override fun getChapterById(chapterId: String?): Flow<Chapter?> = chaptersDao.getChapterById(chapterId)

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateChapter(chapter: Chapter) = chaptersDao.updateChapter(chapter)


    override suspend fun loadChapterDataFromRemote(
        chapterId: String,
        callback: FirestoreDocumentCallback<Chapter>
    ) =
        firestoreService.getDetailedChapterById(chapterId, callback)
}