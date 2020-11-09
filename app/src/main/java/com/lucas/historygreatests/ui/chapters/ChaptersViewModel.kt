package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.chapters.FirestoreChaptersService
import com.lucas.historygreatests.utils.extensions.addRange

class ChaptersViewModel : BaseViewModel(), IChaptersViewModel, IPaginationViewModel {

    override val chapters = MutableLiveData<List<Chapter>>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    override val firestoreService = FirestoreChaptersService()

    override fun getQuery(bookId: String, callback: FirestoreQueryCallback<Chapter>) = firestoreService.getChaptersByBookId(bookId, callback)

    override fun loadChapters(bookId: String) {
        errorLoading.value = false
        loading.value = true

        getQuery(bookId, object : FirestoreQueryCallback<Chapter> {
            override fun onSuccess(result: List<Chapter>?) {
                chapters.value = result
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

        getQuery(itemId, object : FirestoreQueryCallback<Chapter> {
            override fun onSuccess(result: List<Chapter>?) {
                chapters.addRange(result)
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