package com.lucas.historygreatests.ui.home

import android.app.Application
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lucas.historygreatests.database.TopicsRoomDatabase
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.TopicRepository
import com.lucas.historygreatests.services.FirestoreQueryCallback
import com.lucas.historygreatests.services.topics.FirestoreTopicsService
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(application: Application) : BaseViewModel(application), IHomeViewModel {


    override val firestoreService = FirestoreTopicsService()

    override val repository = TopicRepository(
        TopicsRoomDatabase.getDatabase(context).topicsDao()
    )

    override val topics = repository.allTopics.asLiveData()

    override fun loadTopics() {
        if (topics.value != null && topics.value?.size!! > 0) return

        errorLoading.value = false
        loading.value = true

        firestoreService.getHomeTopics(object : FirestoreQueryCallback<Topic> {
            override fun onSuccess(result: List<Topic>?) {
                if (result != null) storeLocalTopics(result)
            }

            override fun onFailed(exception: Exception) {
                errorLoading.value = true
            }

            override fun onCompleted() {
                loading.value = false
            }

        })
    }

    override fun storeLocalTopics(topicList: List<Topic>) = viewModelScope.launch {
        repository.insertList(topicList)
    }
}