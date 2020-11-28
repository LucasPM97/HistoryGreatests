package com.lucas.historygreatests.services.books

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.services.FirestoreConstants
import com.lucas.historygreatests.services.FirestoreDatabase
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.extensions.getPagination

class FirestoreBooksService : FirestoreDatabase(), IFirestoreBooksService {


    override fun getBooksByTopicIdWithSnapshot(
        topicId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Book>
    ) {
        val query = db.collection(FirestoreConstants.Topics.COLLECTION)
            .document(topicId)
            .collection(FirestoreConstants.Topics.BOOKS_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS, Query.Direction.DESCENDING)

        query.getPagination(limit = 1, lastDocument = lastDocumentSnapshot, callback = callback)

    }

    override fun getBooksByTopicIdWithDocumentId(
        topicId: String,
        lastDocumentId: String,
        callback: FirestorePaginationQueryCallback<Book>
    ) {

        getTopicBooksByIdTask(topicId, lastDocumentId)
            .addOnFailureListener {
                callback.onFailed(it)
            }
            .addOnCanceledListener {
                callback.onFailed(null)
            }
            .addOnSuccessListener { documenSnapshot ->
                getBooksByTopicIdWithSnapshot(topicId, documenSnapshot, callback)
            }
    }

    override fun getTopicBooksByIdTask(
        topicId: String,
        lastDocumentId: String
    ): Task<DocumentSnapshot> {
        return db.collection(FirestoreConstants.Topics.COLLECTION)
            .document(topicId)
            .collection(FirestoreConstants.Topics.BOOKS_SUBCOLLECTION)
            .document(lastDocumentId)
            .get()
    }


}