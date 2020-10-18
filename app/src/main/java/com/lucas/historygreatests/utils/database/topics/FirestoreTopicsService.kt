package com.lucas.historygreatests.utils.database.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreCallback
import com.lucas.historygreatests.utils.database.FirestoreConstants
import com.lucas.historygreatests.utils.database.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.get

class FirestoreTopicsService: FirestoreDatabase(), IFirestoreTopicsService {

    override fun getHomeTopics(callback: FirestoreCallback<List<Topic>>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .get(callback)
    }

}