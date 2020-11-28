package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.LiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.repositories.interfaces.IDetailedChapterRepository
import kotlinx.coroutines.Job

interface IChapterDetailedViewModel {

    val repository: IDetailedChapterRepository

    fun chapterDetails(chapterId: String): LiveData<Chapter?>

    fun loadChapter(chapterId: String)

    fun shouldLoadFromRemote(): Boolean

    fun storeChapterUpdates(chapter: Chapter): Job
}