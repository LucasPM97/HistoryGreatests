package com.lucas.historygreatests.models.viewModels

import androidx.lifecycle.MutableLiveData

interface IPaginationViewModel {

    val isLoadingMore: MutableLiveData<Boolean>
    val errorLoadingMore: MutableLiveData<Boolean>

    fun loadMoreItems(itemId:String)
}