package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreBooksService
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.FirestoreTopicsService
import java.lang.Exception

class BooksViewModel: BaseViewModel() {

    val books = MutableLiveData<List<Book>>()

    private val firestoreService = FirestoreBooksService()

    fun loadBooks(topicId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getBooks(topicId, object : FirestoreCallback<List<Book>> {
            override fun onSuccess(result: List<Book>?) {
                books.value = result
            }

            override fun onFailed(exception: Exception) {
                errorLoading.value = true
            }

            override fun onCompleted() {
                loading.value = false
            }

        })
    }


}