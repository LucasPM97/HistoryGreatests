package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.books.FirestoreBooksService

class BooksViewModel: BaseViewModel(), IBooksViewModel {

    override val books = MutableLiveData<List<Book>>()

    override val firestoreService = FirestoreBooksService()

    override fun loadBooks(topicId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getBooksByTopicId(topicId, object : FirestoreQueryCallback<Book> {
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