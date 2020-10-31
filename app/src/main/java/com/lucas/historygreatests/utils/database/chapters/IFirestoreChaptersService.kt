package com.lucas.historygreatests.utils.database.chapters

import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreDocumentCallback
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback

interface IFirestoreChaptersService {

    fun getChaptersByBookId(bookId:String, callback: FirestoreQueryCallback<Chapter>)

    fun getDetailedChapterById(chapterId:String, callback: FirestoreDocumentCallback<Chapter>)
}