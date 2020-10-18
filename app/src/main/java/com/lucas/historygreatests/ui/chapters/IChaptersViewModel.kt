package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreChaptersService

interface IChaptersViewModel {

    val chapters: MutableLiveData<List<Chapter>>
    val firestoreService:FirestoreChaptersService

    fun loadChapters(chapterId:String)
}