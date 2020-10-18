package com.lucas.historygreatests.ui.chapters

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.chapters.FirestoreChaptersService

class ChaptersViewModel: BaseViewModel(),IChaptersViewModel {

    override val chapters = MutableLiveData<List<Chapter>>()
    override val firestoreService = FirestoreChaptersService()

    override fun loadChapters(chapterId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getChaptersByBookId(chapterId,object : FirestoreCallback<List<Chapter>> {
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
}