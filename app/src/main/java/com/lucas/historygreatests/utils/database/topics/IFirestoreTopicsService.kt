package com.lucas.historygreatests.utils.database.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback

interface IFirestoreTopicsService {
    fun getHomeTopics(callback: FirestoreQueryCallback<Topic>)
}