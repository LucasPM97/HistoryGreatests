package com.lucas.historygreatests.services.books

import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.services.FirestoreConstants
import com.lucas.historygreatests.services.FirestoreDatabase
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.extensions.getPagination

class FirestoreBooksService : FirestoreDatabase(), IFirestoreBooksService {


    override fun getBooksByTopicId(
        topicId: String,
        lastDocumentSnapshot: DocumentSnapshot?,
        callback: FirestorePaginationQueryCallback<Book>
    ) {
        val query = db.collection(FirestoreConstants.Topics.COLLECTION)
            .document(topicId)
            .collection(FirestoreConstants.Topics.BOOKS_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)

        query.getPagination(limit = 5, lastDocument = lastDocumentSnapshot, callback = callback)

    }

}