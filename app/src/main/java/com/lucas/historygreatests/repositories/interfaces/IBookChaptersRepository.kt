package com.lucas.historygreatests.repositories.interfaces

import androidx.annotation.WorkerThread
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.flow.Flow

interface IBookChaptersRepository {

    val firestoreService: FirestoreChaptersService

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun getChaptersByBookId(bookId: String): Flow<List<Chapter>>

    suspend fun getLastDocumentId(): String?

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertList(chapters: List<Chapter>, refresh: Boolean = false)

    suspend fun loadBookChaptersFromRemote(
        bookId: String,
        callback: FirestorePaginationQueryCallback<Chapter>,
        lastDocumentSnapshot: DocumentSnapshot? = null
    )
}