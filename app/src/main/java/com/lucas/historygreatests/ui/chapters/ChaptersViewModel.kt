package com.lucas.historygreatests.ui.chapters

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.R
import com.lucas.historygreatests.database.ChaptersRoomDatabase
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.repositories.ChapterRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import com.lucas.historygreatests.utils.helpers.DatetimeHelper
import kotlinx.coroutines.launch
import java.util.*

class ChaptersViewModel(application: Application) : BaseViewModel(application), IChaptersViewModel,
    IPaginationViewModel {

    override val repository = ChapterRepository(
        ChaptersRoomDatabase.getDatabase(context).chaptersDao()
    )

    override val chapters = repository.allChapters.asLiveData()
    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    private fun shouldReloadList(): Boolean {
        try {
            val preferences = context.getSharedPreferences(
                context.getString(R.string.greatest_settings),
                Context.MODE_PRIVATE
            )

            if (!preferences.contains(context.getString(R.string.chapter_db_expire_date))) return true

            val expireDate =
                Date(preferences.getLong(context.getString(R.string.chapter_db_expire_date), 0))

            return DatetimeHelper.checkIfExpirationDateHasExpired(expireDate)
        } catch (exception: java.lang.Exception) {
            return true
        }
    }

    override fun loadChapters(bookId: String) {
        if (!shouldReloadList()) return

        errorLoading.value = false
        loading.value = true
        viewModelScope.launch {
            repository.loadChaptersFromRemote(
                bookId,
                object : FirestorePaginationQueryCallback<Chapter> {
                    override fun onSuccess(
                        result: List<Chapter>?,
                        lastDocument: DocumentSnapshot?
                    ) {
                        if (result != null) storeLocalChapters(result)
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
            repository.loadChaptersFromRemote(
                itemId,
                object : FirestorePaginationQueryCallback<Chapter> {
                    override fun onSuccess(
                        result: List<Chapter>?,
                        lastDocument: DocumentSnapshot?
                    ) {

                        if (result != null) storeLocalChapters(result)
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

    override fun storeLocalChapters(chapterList: List<Chapter>, refresh: Boolean) =
        viewModelScope.launch {

            val preferences = context.getSharedPreferences(
                context.getString(R.string.greatest_settings),
                Context.MODE_PRIVATE
            )

            preferences.edit(commit = true) {
                putLong(
                    context.getString(R.string.chapter_db_expire_date),
                    DatetimeHelper.getCurrentDate().time
                )
            }

            repository.insertList(chapterList, refresh)
        }
}