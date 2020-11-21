package com.lucas.historygreatests.repositories.services

import java.lang.Exception

interface FirestoreQueryCallback<T> {

    fun onSuccess(result: List<T>?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}