package com.lucas.historygreatests.utils.database.books

import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.FirestoreConstants
import com.lucas.historygreatests.utils.database.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.get

class FirestoreBooksService: FirestoreDatabase(), IFirestoreBooksService {

    override fun getBooksByTopicId(topicId:String, callback: FirestoreQueryCallback<Book>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .document(topicId)
            .collection(FirestoreConstants.Topics.BOOKS_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .get(callback)
    }

}