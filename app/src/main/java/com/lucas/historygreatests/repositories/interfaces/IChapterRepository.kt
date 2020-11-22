package com.lucas.historygreatests.repositories.interfaces

import androidx.annotation.WorkerThread
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.flow.Flow

interface IChapterRepository {

    val firestoreService: FirestoreChaptersService

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allChapters: Flow<List<Chapter>>

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertList(chapters: List<Chapter>, refresh: Boolean = false)

    fun loadChaptersFromRemote(
        bookId: String,
        callback: FirestorePaginationQueryCallback<Chapter>,
        lastDocumentSnapshot: DocumentSnapshot? = null
    )
}