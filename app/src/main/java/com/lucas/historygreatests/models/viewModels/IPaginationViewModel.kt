package com.lucas.historygreatests.models.viewModels

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot

interface IPaginationViewModel {

    val lastDocumentSnapshot: MutableLiveData<DocumentSnapshot>
    val isLoadingMore: MutableLiveData<Boolean>
    val errorLoadingMore: MutableLiveData<Boolean>

    fun loadMoreItems(itemId:String)
}