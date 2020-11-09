package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.books.IFirestoreBooksService

interface IBooksViewModel {

    val books:MutableLiveData<List<Book>>

    val firestoreService: IFirestoreBooksService

    fun loadBooks(topicId:String)

    fun getQuery(topicId:String, callback: FirestoreQueryCallback<Book>)
}