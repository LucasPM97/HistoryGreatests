package com.lucas.historygreatests.utils.database.chapters

import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.*
import com.lucas.historygreatests.utils.extensions.getDocument
import com.lucas.historygreatests.utils.extensions.getPagination

class FirestoreChaptersService: FirestoreDatabase(), IFirestoreChaptersService {

    override fun getChaptersByBookId(bookId:String, lastDocumentSnapshot: DocumentSnapshot?, callback: FirestorePaginationQueryCallback<Chapter>) {
        val query = db.collection(FirestoreConstants.Books.COLLECTION)
            .document(bookId)
            .collection(FirestoreConstants.Books.CHAPTER_SUBCOLLECTION)
            .orderBy(FirestoreConstants.Books.Indexes.CHAPTER_SUBCOLLECTION_ORDER)
        query.getPagination(limit = 5, lastDocument = lastDocumentSnapshot,callback = callback)
    }


    override fun getDetailedChapterById(chapterId:String, callback: FirestoreDocumentCallback<Chapter>) {
        db.collection(FirestoreConstants.Chapters.COLLECTION)
            .document(chapterId)
            .getDocument(callback)
    }


}