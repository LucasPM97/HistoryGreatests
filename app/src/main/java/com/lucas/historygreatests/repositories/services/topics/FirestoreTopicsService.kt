package com.lucas.historygreatests.repositories.services.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.repositories.services.FirestoreQueryCallback
import com.lucas.historygreatests.repositories.services.FirestoreConstants
import com.lucas.historygreatests.repositories.services.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.getList

class FirestoreTopicsService: FirestoreDatabase(), IFirestoreTopicsService {

    override fun getHomeTopics(callback: FirestoreQueryCallback<Topic>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .getList(callback)
    }

}