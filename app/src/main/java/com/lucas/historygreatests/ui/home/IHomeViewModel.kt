package com.lucas.historygreatests.ui.home

import androidx.lifecycle.LiveData
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.TopicRepository
import com.lucas.historygreatests.services.topics.IFirestoreTopicsService
import kotlinx.coroutines.Job

interface IHomeViewModel {
    val topics: LiveData<List<Topic>>

    val repository: TopicRepository

    fun loadTopics()

    fun storeLocalTopics(topicList: List<Topic>): Job
}