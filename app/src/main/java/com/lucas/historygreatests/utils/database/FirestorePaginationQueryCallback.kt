package com.lucas.historygreatests.utils.database

import com.google.firebase.firestore.DocumentSnapshot
import java.lang.Exception

interface FirestorePaginationQueryCallback<T> {

    fun onSuccess(result: List<T>?, lastDocument: DocumentSnapshot?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}