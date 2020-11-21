package com.lucas.historygreatests.ui.home

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.services.FirestoreQueryCallback
import com.lucas.historygreatests.repositories.services.topics.FirestoreTopicsService
import java.lang.Exception

class HomeViewModel : BaseViewModel(), IHomeViewModel {

    override val topics: MutableLiveData<List<Topic>> = MutableLiveData<List<Topic>>()

    override val firestoreService = FirestoreTopicsService()

    override fun loadTopics() {
        if(topics.value != null && topics.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        firestoreService.getHomeTopics(object : FirestoreQueryCallback<Topic> {
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