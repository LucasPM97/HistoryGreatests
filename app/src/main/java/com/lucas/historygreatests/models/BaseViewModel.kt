package com.lucas.historygreatests.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val errorLoading = MutableLiveData<Boolean>()
}