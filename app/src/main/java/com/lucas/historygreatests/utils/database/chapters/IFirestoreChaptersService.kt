package com.lucas.historygreatests.utils.database.chapters

import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreCallback

interface IFirestoreChaptersService {

    fun getChaptersByBookId(bookId:String, callback: FirestoreCallback<List<Chapter>>)

    fun getDetailedChapterById(chapterId:String, callback: FirestoreCallback<Chapter>)
}