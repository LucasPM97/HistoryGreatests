package com.lucas.historygreatests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.GoogleAuthProvider
import com.lucas.historygreatests.models.User
import com.lucas.historygreatests.utils.AuthenticationManager

class UserViewModel: ViewModel(){

    var user = MutableLiveData<User>()

    fun initViewModel (){
        AuthenticationManager.authStateListener{
            if(it.currentUser == null) user.value = null
        }
    }

    fun authenticateWithFirebase(googleTokenId: String?) {
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        AuthenticationManager.loginWithFirebaseGoogle(googleAuthCredential).addOnCompleteListener { authTask->
            if (authTask.isSuccessful) {
                val isNewUser = authTask.result?.additionalUserInfo?.isNewUser
                AuthenticationManager.getUser()?.let {
                    user.value = AuthenticationManager.mapFirebaseUser(it)
                }
            }
        }
    }

    private fun logout() {
        user.value = null
        AuthenticationManager.logout()
    }

    fun isLogged(): Boolean {
        val isLogged = AuthenticationManager.isAuthenticated()

        if (isLogged) user.value = AuthenticationManager.getUser()?.let {
            AuthenticationManager.mapFirebaseUser(
                it
            )
        }

        return isLogged
    }
}