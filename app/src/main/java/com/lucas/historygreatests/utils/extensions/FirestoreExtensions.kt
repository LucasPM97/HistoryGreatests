package com.lucas.historygreatests.utils.extensions

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.FirestorePaginationQueryCallback
import com.lucas.historygreatests.services.FirestoreQueryCallback


inline fun <reified T> Query.getList(callback: FirestoreQueryCallback<T>){
    this.get()
        .addOnSuccessListener { result ->

            callback.onSuccess(
                result.toObjects(T::class.java)
            )
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }
}

inline fun <reified T> Query.getPagination(lastDocument: DocumentSnapshot?,limit: Long,callback: FirestorePaginationQueryCallback<T> ){

    var query = this

    if (lastDocument != null) {
        query = query.startAfter(lastDocument)
    }
    query.limit(limit).get()
        .addOnSuccessListener { result ->

            val lastVisibleDocument = if(!result.isEmpty) result.documents[result.size() - 1] else null

            callback.onSuccess(
                result.toObjects(T::class.java),
                lastVisibleDocument
            )
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }
}

inline fun <reified T> DocumentReference.getDocument(callback: FirestoreDocumentCallback<T>) {
    this.get()
        .addOnSuccessListener {
            callback.onSuccess(
                it.toObject(T::class.java)
            )
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }

}