package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.books.FirestoreBooksService
import com.lucas.historygreatests.utils.extensions.addRange

class BooksViewModel : BaseViewModel(), IBooksViewModel, IPaginationViewModel {

    override val books = MutableLiveData<List<Book>>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    override val firestoreService = FirestoreBooksService()



    override fun getQuery(topicId: String, callback: FirestoreQueryCallback<Book>) = firestoreService.getBooksByTopicId(topicId,callback)

    override fun loadBooks(topicId: String) {
        errorLoading.value = false
        loading.value = true

        getQuery(topicId, object : FirestoreQueryCallback<Book> {
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


    override fun loadMoreItems(itemId: String) {
        errorLoadingMore.value = false
        isLoadingMore.value = true

        getQuery(itemId, object : FirestoreQueryCallback<Book> {
            override fun onSuccess(result: List<Book>?) {
                books.addRange(result)
            }

            override fun onFailed(exception: Exception) {
                errorLoadingMore.value = true
            }

            override fun onCompleted() {
                isLoadingMore.value = false
            }

        })
    }


}