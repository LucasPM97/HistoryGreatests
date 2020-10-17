package com.lucas.historygreatests.utils.database

import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.extensions.get

class FirestoreBooksService: FirestoreDatabase() {

    fun getBooks(topicId:String,callback: FirestoreCallback<List<Book>>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .document(topicId)
            .collection(FirestoreConstants.Topics.BOOKS_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .get(callback)
    }

}