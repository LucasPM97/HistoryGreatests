package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.lucas.historygreatests.models.Book
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.models.viewModels.IPaginationViewModel
import com.lucas.historygreatests.utils.database.FirestorePaginationQueryCallback
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.chapters.FirestoreChaptersService
import com.lucas.historygreatests.utils.extensions.addRange

class ChaptersViewModel : BaseViewModel(), IChaptersViewModel, IPaginationViewModel {

    override val chapters = MutableLiveData<List<Chapter>>()
    override val lastDocumentSnapshot = MutableLiveData<DocumentSnapshot>()
    override val isLoadingMore = MutableLiveData<Boolean>()
    override val errorLoadingMore = MutableLiveData<Boolean>()

    override val firestoreService = FirestoreChaptersService()

    override fun getQuery(bookId: String, callback: FirestorePaginationQueryCallback<Chapter>) =
        firestoreService.getChaptersByBookId(bookId, lastDocumentSnapshot.value, callback)

    override fun loadChapters(bookId: String) {
        if(chapters.value != null && chapters.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        getQuery(bookId, object : FirestorePaginationQueryCallback<Chapter> {
            override fun onSuccess(result: List<Chapter>?, lastDocument: DocumentSnapshot?) {
                chapters.value = result
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

        getQuery(itemId, object : FirestorePaginationQueryCallback<Chapter> {
            override fun onSuccess(result: List<Chapter>?, lastDocument: DocumentSnapshot?) {
                chapters.addRange(result)
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