package com.lucas.historygreatests.services.chapters

import com.google.firebase.firestore.DocumentSnapshot
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
}