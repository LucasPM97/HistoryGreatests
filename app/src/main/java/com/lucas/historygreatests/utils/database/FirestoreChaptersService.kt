package com.lucas.historygreatests.utils.database

import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.extensions.get

class FirestoreChaptersService: FirestoreDatabase() {

    fun getChapters(bookId:String,callback: FirestoreCallback<List<Chapter>>) {
        db.collection(FirestoreConstants.Chapters.COLLECTION)
            .whereEqualTo(FirestoreConstants.Chapters.QueryParams.BOOK_ID, bookId)
            .orderBy(FirestoreConstants.Chapters.Indexes.ORDER)
            .get(callback)
    }

}