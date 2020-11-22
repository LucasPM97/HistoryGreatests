package com.lucas.historygreatests.ui.books

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.database.BooksRoomDatabase
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.repositories.BookRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.books.FirestoreBooksService
import kotlinx.coroutines.launch

class BooksViewModel(application: Application) : BaseViewModel(application), IBooksViewModel,
    IPaginationViewModel {

    override val repository = BookRepository(
        BooksRoomDatabase.getDatabase(context).booksDao()
    )

    override val books = repository.allBooks.asLiveData()

    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    override val firestoreService = FirestoreBooksService()

    override fun getQuery(topicId: String, callback: FirestorePaginationQueryCallback<Book>) =
        firestoreService.getBooksByTopicId(topicId, lastDocumentSnapshot.value, callback)

    override fun loadBooks(topicId: String) {
        if (books.value != null && books.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        getQuery(topicId, object : FirestorePaginationQueryCallback<Book> {
            override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                if (result != null) storeLocalBooks(result, true)
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
        if (loading.value == true || isLoadingMore.value == true) return

        errorLoadingMore.value = false
        isLoadingMore.value = true

        getQuery(itemId, object : FirestorePaginationQueryCallback<Book> {
            override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                if (result != null) storeLocalBooks(result)
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

    override fun storeLocalBooks(bookList: List<Book>, refresh: Boolean) =
        viewModelScope.launch {
            repository.insertList(bookList, refresh)
        }

}