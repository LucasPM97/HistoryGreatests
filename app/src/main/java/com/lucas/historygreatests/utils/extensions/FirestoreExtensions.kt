package com.lucas.historygreatests.utils.extensions

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.lucas.historygreatests.utils.database.FirestoreDocumentCallback
import com.lucas.historygreatests.utils.database.FirestoreQueryCallback


inline fun <reified T> Query.get(callback: FirestoreQueryCallback<T>) {
    this.get()
        .addOnSuccessListener { result ->
            callback.onSuccess(
                result.toObjects(T::class.java)
            )
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }
}

inline fun <reified T> DocumentReference.get(callback: FirestoreDocumentCallback<T>) {
    this.get()
        .addOnSuccessListener {
            callback.onSuccess(
                it.toObject(T::class.java)
            )
        }
        .addOnFailureListener { ex -> callback.onFailed(ex) }
        .addOnCompleteListener { callback.onCompleted() }

}