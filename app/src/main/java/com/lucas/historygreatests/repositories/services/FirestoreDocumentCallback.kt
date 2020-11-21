package com.lucas.historygreatests.repositories.services

import java.lang.Exception

interface FirestoreDocumentCallback<T> {

    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}