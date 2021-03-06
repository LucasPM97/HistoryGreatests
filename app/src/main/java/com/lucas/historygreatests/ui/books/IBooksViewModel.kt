package com.lucas.historygreatests.ui.books

import androidx.lifecycle.LiveData
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.repositories.BookRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.books.IFirestoreBooksService
import kotlinx.coroutines.Job

interface IBooksViewModel {

    fun books(topicId: String): LiveData<List<Book>>

    val repository: BookRepository

    fun loadBooks(topicId: String)

    fun storeLocalBooks(topicId: String, bookList: List<Book>, refresh: Boolean = false): Job
}