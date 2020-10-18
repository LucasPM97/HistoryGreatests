package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.utils.database.books.FirestoreBooksService
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.books.IFirestoreBooksService
import java.lang.Exception

class BooksViewModel: BaseViewModel(), IBooksViewModel {

    override val books = MutableLiveData<List<Book>>()

    override val firestoreService = FirestoreBooksService()

    override fun loadBooks(topicId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getBooksByTopicId(topicId, object : FirestoreCallback<List<Book>> {
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