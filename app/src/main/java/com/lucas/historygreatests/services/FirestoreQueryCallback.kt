package com.lucas.historygreatests.services

import java.lang.Exception

interface FirestoreQueryCallback<T> {

    fun onSuccess(result: List<T>?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}