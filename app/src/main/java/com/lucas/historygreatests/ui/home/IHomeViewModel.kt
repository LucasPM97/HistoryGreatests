package com.lucas.historygreatests.ui.home

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.topics.FirestoreTopicsService
import com.lucas.historygreatests.utils.database.topics.IFirestoreTopicsService

interface IHomeViewModel {
    val topics:MutableLiveData<List<Topic>>

    val firestoreService: IFirestoreTopicsService

    fun loadTopics()
}