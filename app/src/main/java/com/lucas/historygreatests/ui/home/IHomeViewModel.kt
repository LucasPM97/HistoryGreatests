package com.lucas.historygreatests.ui.home

import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreTopicsService

interface IHomeViewModel {
    val topics:MutableLiveData<List<Topic>>

    val firestoreService:FirestoreTopicsService

    fun loadTopics()
}