package com.lucas.historygreatests.utils.database.chapters

import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreDocumentCallback
import com.lucas.historygreatests.utils.database.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback

interface IFirestoreChaptersService {

    fun getChaptersByBookId(
        bookId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Chapter>
    )

    fun getDetailedChapterById(chapterId: String, callback: FirestoreDocumentCallback<Chapter>)
}