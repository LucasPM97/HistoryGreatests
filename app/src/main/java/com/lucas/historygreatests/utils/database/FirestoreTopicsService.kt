package com.lucas.historygreatests.utils.database

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.extensions.get

class FirestoreTopicsService: FirestoreDatabase() {

    fun getHomeTopics(callback: FirestoreCallback<List<Topic>>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .get(callback)
    }

}