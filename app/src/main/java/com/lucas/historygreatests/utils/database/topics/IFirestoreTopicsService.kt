package com.lucas.historygreatests.utils.database.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreCallback

interface IFirestoreTopicsService {
    fun getHomeTopics(callback: FirestoreCallback<List<Topic>>)
}