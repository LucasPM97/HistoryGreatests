package com.lucas.historygreatests.services.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.services.FirestoreQueryCallback
import com.lucas.historygreatests.services.FirestoreConstants
import com.lucas.historygreatests.services.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.getList

class FirestoreTopicsService: FirestoreDatabase(), IFirestoreTopicsService {

    override fun getHomeTopics(callback: FirestoreQueryCallback<Topic>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .getList(callback)
    }

}