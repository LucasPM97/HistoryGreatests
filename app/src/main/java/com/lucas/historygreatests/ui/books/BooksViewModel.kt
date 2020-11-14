package com.lucas.historygreatests.ui.books

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.utils.database.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.database.books.FirestoreBooksService
import com.lucas.historygreatests.utils.extensions.addRange

class BooksViewModel : BaseViewModel(), IBooksViewModel, IPaginationViewModel {

    override val books = MutableLiveData<List<Book>>()
    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    override val firestoreService = FirestoreBooksService()

    override fun getQuery(topicId: String, callback: FirestorePaginationQueryCallback<Book>) =
        firestoreService.getBooksByTopicId(topicId, lastDocumentSnapshot.value, callback)

    override fun loadBooks(topicId: String) {
        if(books.value != null && books.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        getQuery(topicId, object : FirestorePaginationQueryCallback<Book> {
            override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                books.value = result
                lastDocumentSnapshot.value = lastDocument
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
        if(loading.value == true ||  isLoadingMore.value == true) return

        errorLoadingMore.value = false
        isLoadingMore.value = true

        getQuery(itemId, object : FirestorePaginationQueryCallback<Book> {
            override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                books.addRange(result)
                lastDocumentSnapshot.value = lastDocument ?: lastDocumentSnapshot.value
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