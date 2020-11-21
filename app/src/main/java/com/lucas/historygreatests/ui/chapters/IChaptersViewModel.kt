package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.IFirestoreChaptersService

interface IChaptersViewModel {

    val chapters: MutableLiveData<List<Chapter>>
    val firestoreService: IFirestoreChaptersService

    fun loadChapters(bookId:String)

    fun getQuery(bookId:String, callback: FirestorePaginationQueryCallback<Chapter>)
}