package com.lucas.historygreatests.repositories.services.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.services.FirestoreQueryCallback

interface IFirestoreTopicsService {
    fun getHomeTopics(callback: FirestoreQueryCallback<Topic>)
}