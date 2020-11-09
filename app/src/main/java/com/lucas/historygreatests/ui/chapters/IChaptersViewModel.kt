package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.chapters.IFirestoreChaptersService

interface IChaptersViewModel {

    val chapters: MutableLiveData<List<Chapter>>
    val firestoreService: IFirestoreChaptersService

    fun loadChapters(bookId:String)

    fun getQuery(bookId:String, callback: FirestoreQueryCallback<Chapter>)
}