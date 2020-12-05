package com.lucas.historygreatests.services

import java.lang.Exception

interface FirestoreDocumentCallback<T> {

    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}