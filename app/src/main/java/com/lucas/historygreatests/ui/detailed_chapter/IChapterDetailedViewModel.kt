package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.repositories.services.chapters.IFirestoreChaptersService

interface IChapterDetailedViewModel {

    val chapter: MutableLiveData<Chapter>
    val firestoreService: IFirestoreChaptersService

    fun setup(args: ChapterDetailedFragmentArgs)

    fun loadChapter(chapterId:String)
}