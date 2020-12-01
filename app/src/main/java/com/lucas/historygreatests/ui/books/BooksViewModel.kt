package com.lucas.historygreatests.ui.books

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.R
import com.lucas.historygreatests.database.BooksRoomDatabase
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.repositories.BookRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.helpers.DatabaseHelper
import kotlinx.coroutines.launch
import java.util.*

class BooksViewModel(application: Application) : BaseViewModel(application), IBooksViewModel,
    IPaginationViewModel {

    override val repository = BookRepository(
        BooksRoomDatabase.getDatabase(context).booksDao()
    )

    override fun books(topicId: String) = repository.getTopicBooksFlow(topicId).asLiveData()

    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()


    private fun shouldReloadList() =
        DatabaseHelper.checkIfDatabaseIsExpired(context, R.string.book_db_expire_date)

    override fun loadBooks(topicId: String) {
        viewModelScope.launch {
            if (!shouldReloadList()) return@launch

            errorLoading.value = false
            loading.value = true

            repository.getBooksFromRemote(topicId, object : FirestorePaginationQueryCallback<Book> {
                override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                    if (result != null) storeLocalBooks(topicId, result, true)
                    lastDocumentSnapshot.value = lastDocument
                }

                override fun onFailed(exception: Exception?) {
                    errorLoading.value = true
                }

                override fun onCompleted() {
                    loading.value = false
                }
            })
        }
    }

    override fun loadMoreItems(itemId: String) {
        viewModelScope.launch {
            if (loading.value == true || isLoadingMore.value == true) return@launch

            errorLoadingMore.value = false
            isLoadingMore.value = true
            repository.getBooksFromRemote(itemId, object : FirestorePaginationQueryCallback<Book> {
                override fun onSuccess(result: List<Book>?, lastDocument: DocumentSnapshot?) {
                    if (result != null) storeLocalBooks(itemId, result)
                    lastDocumentSnapshot.value = lastDocument ?: lastDocumentSnapshot.value
                }

                override fun onFailed(exception: Exception?) {
                    errorLoadingMore.value = true
                }

                override fun onCompleted() {
                    isLoadingMore.value = false
                }

            }, lastDocumentSnapshot.value)
        }
    }

    override fun storeLocalBooks(topicId: String, bookList: List<Book>, refresh: Boolean) =
        viewModelScope.launch {

            val newList = bookList.map {
                it.setTopicId(topicId)
            }

            DatabaseHelper.addDatabaseIsExpired(context, R.string.book_db_expire_date)
            repository.insertList(newList, refresh)
        }

}