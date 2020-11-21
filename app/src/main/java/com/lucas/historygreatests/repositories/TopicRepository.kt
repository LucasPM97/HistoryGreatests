package com.lucas.historygreatests.repositories

import androidx.annotation.WorkerThread
import com.lucas.historygreatests.database.daos.TopicsDao
import com.lucas.historygreatests.models.Topic
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TopicRepository(private val topicsDao: TopicsDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allTopics: Flow<List<Topic>> = topicsDao.getTopics()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(topic: Topic) {
        topicsDao.insert(topic)
    }
}