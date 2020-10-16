package com.lucas.historygreatests.utils.database

import java.lang.Exception

interface FirestoreCallback<T> {

    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)

    fun onCompleted()
}