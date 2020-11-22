package com.lucas.historygreatests.models.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application){
    val loading = MutableLiveData<Boolean>()
    val errorLoading = MutableLiveData<Boolean>()

    val context = getApplication<Application>().applicationContext
}