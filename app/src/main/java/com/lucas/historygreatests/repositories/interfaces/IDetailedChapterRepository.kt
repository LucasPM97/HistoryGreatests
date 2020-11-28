package com.lucas.historygreatests.repositories.interfaces

import androidx.annotation.WorkerThread
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.flow.Flow

interface IDetailedChapterRepository {

    val firestoreService: FirestoreChaptersService
    fun getChapterById(chapterId: String?): Flow<Chapter?>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateChapter(chapter: Chapter)

    suspend fun loadChapterDataFromRemote(
        chapterId: String,
        callback: FirestoreDocumentCallback<Chapter>,
    )
}