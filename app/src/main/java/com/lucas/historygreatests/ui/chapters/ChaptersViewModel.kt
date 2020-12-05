package com.lucas.historygreatests.ui.chapters

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.R
import com.lucas.historygreatests.database.ChaptersRoomDatabase
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.repositories.BookChaptersRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.helpers.DatabaseHelper
import kotlinx.coroutines.launch

class ChaptersViewModel(application: Application) : BaseViewModel(application), IChaptersViewModel,
    IPaginationViewModel {

    override val repository = BookChaptersRepository(
        ChaptersRoomDatabase.getDatabase(context).chaptersDao()
    )

    override fun chapters(bookId: String) = repository.getChaptersByBookId(bookId).asLiveData()
    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    private fun shouldReloadList() =
        DatabaseHelper.checkIfDatabaseIsExpired(context, R.string.chapter_db_expire_date)

    override fun loadChapters(bookId: String) {
        if (!shouldReloadList()) return

        errorLoading.value = false
        loading.value = true
        viewModelScope.launch {
            repository.loadBookChaptersFromRemote(
                bookId,
                object : FirestorePaginationQueryCallback<Chapter> {
                    override fun onSuccess(
                        result: List<Chapter>?,
                        lastDocument: DocumentSnapshot?
                    ) {
                        if (result != null) storeLocalChapters(bookId, result)
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
        errorLoadingMore.value = false
        isLoadingMore.value = true


        viewModelScope.launch {
            repository.loadBookChaptersFromRemote(
                itemId,
                object : FirestorePaginationQueryCallback<Chapter> {
                    override fun onSuccess(
                        result: List<Chapter>?,
                        lastDocument: DocumentSnapshot?
                    ) {

                        if (result != null) storeLocalChapters(itemId, result)
                        lastDocumentSnapshot.value = lastDocument ?: lastDocumentSnapshot.value
                    }

                    override fun onFailed(exception: Exception?) {
                        errorLoadingMore.value = true
                    }

                    override fun onCompleted() {
                        isLoadingMore.value = false
                    }

                }, lastDocumentSnapshot.value
            )
        }
    }

    override fun storeLocalChapters(bookId: String, chapterList: List<Chapter>, refresh: Boolean) =
        viewModelScope.launch {

            val newList = chapterList.map {
                it.setBookId(bookId)
            }
            DatabaseHelper.addDatabaseIsExpired(context, R.string.chapter_db_expire_date)

            repository.insertList(newList, refresh)
        }
}