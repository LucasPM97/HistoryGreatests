package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.FirestoreBooksService

interface IBooksViewModel {

    val books:MutableLiveData<List<Book>>

    val firestoreService:FirestoreBooksService

    fun loadBooks(topicId:String)
}