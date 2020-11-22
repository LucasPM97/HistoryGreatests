package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.LiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.ChapterRepository
import com.lucas.historygreatests.repositories.TopicRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.IFirestoreChaptersService
import kotlinx.coroutines.Job

interface IChaptersViewModel {

    val chapters: LiveData<List<Chapter>>

    val repository: ChapterRepository

    fun loadChapters(bookId:String)

    fun storeLocalChapters(chapterList: List<Chapter>, refresh: Boolean = false): Job
}