package com.lucas.historygreatests.utils.database.chapters

import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.FirestoreConstants
import com.lucas.historygreatests.utils.database.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.get

class FirestoreChaptersService: FirestoreDatabase(), IFirestoreChaptersService {

    override fun getChaptersByBookId(bookId:String, callback: FirestoreCallback<List<Chapter>>) {
        db.collection(FirestoreConstants.Books.COLLECTION)
            .document(bookId)
            .collection(FirestoreConstants.Books.CHAPTER_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Books.Indexes.ORDER)
            .get(callback)
    }


    override fun getDetailedChapterById(chapterId:String, callback: FirestoreCallback<Chapter>) {
        db.collection(FirestoreConstants.Chapters.COLLECTION)
            .document(chapterId)
            .get(callback)
    }


}