package com.lucas.historygreatests.ui.home

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.BaseViewModel
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.FirestoreTopicsService
import java.lang.Exception

class HomeViewModel : BaseViewModel(), IHomeViewModel {

    override val topics:MutableLiveData<List<Topic>> = MutableLiveData<List<Topic>>()

    override val firestoreService = FirestoreTopicsService()

    override fun loadTopics(){
        errorLoading.value = false
        loading.value = true

        firestoreService.getHomeTopics(object : FirestoreCallback<List<Topic>> {
            override fun onSuccess(result: List<Topic>?) {
                topics.value = result
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