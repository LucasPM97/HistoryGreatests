package com.lucas.historygreatests.ui.books

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
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
import com.lucas.historygreatests.utils.helpers.DatetimeHelper
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class BooksViewModel(application: Application) : BaseViewModel(application), IBooksViewModel,
    IPaginationViewModel {

    override val repository = BookRepository(
        BooksRoomDatabase.getDatabase(context).booksDao()
    )

    override val books = repository.allBooks.asLiveData()

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
                    if (result != null) storeLocalBooks(result, true)
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
                    if (result != null) storeLocalBooks(result)
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

    override fun storeLocalBooks(bookList: List<Book>, refresh: Boolean) =
        viewModelScope.launch {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.greatest_settings),
                Context.MODE_PRIVATE
            )

            preferences.edit(commit = true) {
                putLong(
                    context.getString(R.string.book_db_expire_date),
                    DatetimeHelper.getCurrentDate().time
                )
            }

            repository.insertList(bookList, refresh)
        }

}