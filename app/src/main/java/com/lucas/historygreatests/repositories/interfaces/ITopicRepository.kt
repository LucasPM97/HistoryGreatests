package com.lucas.historygreatests.repositories.interfaces

import androidx.annotation.WorkerThread
import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.services.FirestoreQueryCallback
import com.lucas.historygreatests.services.topics.FirestoreTopicsService
import kotlinx.coroutines.flow.Flow

interface ITopicRepository {
    val firestoreService: FirestoreTopicsService

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allTopics: Flow<List<Topic>>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertList(topics: List<Topic>)

    fun loadTopicsFromRemote(callback: FirestoreQueryCallback<Topic>) =
        firestoreService.getHomeTopics(callback)
}