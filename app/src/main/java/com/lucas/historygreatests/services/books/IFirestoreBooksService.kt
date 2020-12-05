package com.lucas.historygreatests.services.books

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback

interface IFirestoreBooksService {

    fun getBooksByTopicIdWithSnapshot(
        topicId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Book>
    )

    fun getBooksByTopicIdWithDocumentId(
        topicId: String,
        lastDocumentId: String,
        callback: FirestorePaginationQueryCallback<Book>
    )

    fun getTopicBooksByIdTask(topicId: String, lastDocumentId: String): Task<DocumentSnapshot>

}