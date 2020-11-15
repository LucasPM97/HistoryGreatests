package com.lucas.historygreatests.utils.database.topics

import com.lucas.historygreatests.models.Topic
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback
import com.lucas.historygreatests.utils.database.FirestoreConstants
import com.lucas.historygreatests.utils.database.FirestoreDatabase
import com.lucas.historygreatests.utils.extensions.getList

class FirestoreTopicsService: FirestoreDatabase(), IFirestoreTopicsService {

    override fun getHomeTopics(callback: FirestoreQueryCallback<Topic>) {
        db.collection(FirestoreConstants.Topics.COLLECTION)
            .orderBy(FirestoreConstants.Topics.Indexes.VIEWS)
            .getList(callback)
    }

}