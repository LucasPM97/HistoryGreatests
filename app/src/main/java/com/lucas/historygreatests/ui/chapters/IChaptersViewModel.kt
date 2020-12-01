package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.LiveData
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.repositories.BookChaptersRepository
import kotlinx.coroutines.Job

interface IChaptersViewModel {

    fun chapters(bookId: String): LiveData<List<Chapter>>

    val repository: BookChaptersRepository

    fun loadChapters(bookId: String)

    fun storeLocalChapters(
        bookId: String,
        chapterList: List<Chapter>,
        refresh: Boolean = false
    ): Job
}