package com.lucas.historygreatests.services.chapters

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestoreConstants
import com.lucas.historygreatests.services.FirestoreDatabase
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.extensions.getDocument
import com.lucas.historygreatests.utils.extensions.getPagination

class FirestoreChaptersService : FirestoreDatabase(), IFirestoreChaptersService {

    override fun getChaptersByBookId(
        bookId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Chapter>
    ) {
        val query = db.collection(FirestoreConstants.Books.COLLECTION)
            .document(bookId)
            .collection(FirestoreConstants.Books.CHAPTER_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Books.Indexes.CHAPTER_SUBCOLLECTION_ORDER)
        query.getPagination(limit = 1, lastDocument = lastDocumentSnapshot, callback = callback)
    }

    override fun getChaptersByBookIdWithDocumentId(
        bookId: String,
        lastDocumentId: String,
        callback: FirestorePaginationQueryCallback<Chapter>
    ) {

        getBookChaptersByIdTask(bookId, lastDocumentId)
            .addOnFailureListener {
                callback.onFailed(it)
            }
            .addOnCanceledListener {
                callback.onFailed(null)
            }
            .addOnSuccessListener { documenSnapshot ->
                getChaptersByBookId(bookId, documenSnapshot, callback)
            }
    }


    override fun getDetailedChapterById(
        chapterId: String,
        callback: FirestoreDocumentCallback<Chapter>
    ) {
        db.collection(FirestoreConstants.Chapters.COLLECTION)
            .document(chapterId)
            .getDocument(callback)
    }

    override fun getBookChaptersByIdTask(
        bookId: String,
        lastDocumentId: String
    ): Task<DocumentSnapshot> {
        return db.collection(FirestoreConstants.Books.COLLECTION)
            .document(bookId)
            .collection(FirestoreConstants.Books.CHAPTER_SUBCOLLECTION)
            .document(lastDocumentId)
            .get()
    }


}