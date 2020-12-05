package com.lucas.historygreatests.services.chapters

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback

interface IFirestoreChaptersService {

    fun getChaptersByBookId(
        bookId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Chapter>
    )

    fun getDetailedChapterById(chapterId: String, callback: FirestoreDocumentCallback<Chapter>)
    fun getChaptersByBookIdWithDocumentId(
        bookId: String,
        lastDocumentId: String,
        callback: FirestorePaginationQueryCallback<Chapter>
    )

    fun getBookChaptersByIdTask(bookId: String, lastDocumentId: String): Task<DocumentSnapshot>
}