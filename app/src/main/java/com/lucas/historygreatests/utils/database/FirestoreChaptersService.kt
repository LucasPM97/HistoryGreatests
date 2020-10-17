package com.lucas.historygreatests.utils.database

import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.extensions.get

class FirestoreChaptersService: FirestoreDatabase() {

    fun getChaptersByBookId(bookId:String, callback: FirestoreCallback<List<Chapter>>) {
        db.collection(FirestoreConstants.Books.COLLECTION)
            .document(bookId)
            .collection(FirestoreConstants.Books.CHAPTER_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Books.Indexes.ORDER)
            .get(callback)
    }


    fun getDetailedChapterById(chapterId:String, callback: FirestoreCallback<Chapter>) {
        db.collection(FirestoreConstants.Chapters.COLLECTION)
            .document(chapterId)
            .get(callback)
    }


}