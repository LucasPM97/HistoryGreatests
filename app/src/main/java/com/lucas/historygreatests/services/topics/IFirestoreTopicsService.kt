package com.lucas.historygreatests.services.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.services.FirestoreQueryCallback

interface IFirestoreTopicsService {
    fun getHomeTopics(callback: FirestoreQueryCallback<Topic>)
}