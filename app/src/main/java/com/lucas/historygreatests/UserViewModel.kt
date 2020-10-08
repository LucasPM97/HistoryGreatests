package com.lucas.historygreatests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucas.historygreatests.models.User

class UserViewModel: ViewModel(){

    val user = MutableLiveData<User>()

    fun login():LiveData<User> {
        user.value = User(name = "Juan")
        return user
    }

    fun logout() {
        user.value = null
    }
}