package com.lucas.historygreatests.repositories.interfaces

import androidx.annotation.WorkerThread
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.books.FirestoreBooksService
import kotlinx.coroutines.flow.Flow

interface IBookRepository {
    val firestoreService: FirestoreBooksService

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun getTopicBooksFlow(topicId: String): Flow<List<Book>>
    suspend fun getLastDocumentId(topicId: String): String

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertList(books: List<Book>, refresh: Boolean = false)

    suspend fun getBooksFromRemote(
        topicId: String,
        callback: FirestorePaginationQueryCallback<Book>,
        lastDocumentSnapshot: DocumentSnapshot? = null
    )
}