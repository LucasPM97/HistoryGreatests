package com.lucas.historygreatests.utils.extensions

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.lucas.historygreatests.utils.database.FirestoreCallback


inline fun <reified T> Query.get(callback: FirestoreCallback<List<T>>) {
    this.get()
        .addOnSuccessListener {
            val result = it.toObjects(T::class.java)
            callback.onSuccess(result)
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }
}

inline fun <reified T> DocumentReference.get(callback: FirestoreCallback<T>) {
    this.get()
        .addOnSuccessListener {
            callback.onSuccess(it.toObject(T::class.java))
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }

}