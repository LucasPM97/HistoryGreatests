package com.lucas.historygreatests.ui.chapters

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.database.ChaptersRoomDatabase
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.repositories.ChapterRepository
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService
import kotlinx.coroutines.launch

class ChaptersViewModel(application: Application) : BaseViewModel(application), IChaptersViewModel,
    IPaginationViewModel {

    override val repository = ChapterRepository(
        ChaptersRoomDatabase.getDatabase(context).chaptersDao()
    )

    override val chapters = repository.allChapters.asLiveData()
    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()


    override fun loadChapters(bookId: String) {
        if (chapters.value != null && chapters.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        repository.loadChaptersFromRemote(
            bookId,
            object : FirestorePaginationQueryCallback<Chapter> {
                override fun onSuccess(result: List<Chapter>?, lastDocument: DocumentSnapshot?) {
                    if (result != null) storeLocalChapters(result)
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
        errorLoadingMore.value = false
        isLoadingMore.value = true

        repository.loadChaptersFromRemote(
            itemId,
            object : FirestorePaginationQueryCallback<Chapter> {
                override fun onSuccess(result: List<Chapter>?, lastDocument: DocumentSnapshot?) {

                    if (result != null) storeLocalChapters(result)
                    lastDocumentSnapshot.value = lastDocument ?: lastDocumentSnapshot.value
                }

                override fun onFailed(exception: Exception) {
                    errorLoadingMore.value = true
                }

                override fun onCompleted() {
                    isLoadingMore.value = false
                }

            }, lastDocumentSnapshot.value
        )
    }

    override fun storeLocalChapters(chapterList: List<Chapter>, refresh: Boolean) =
        viewModelScope.launch {
            repository.insertList(chapterList)
        }
}