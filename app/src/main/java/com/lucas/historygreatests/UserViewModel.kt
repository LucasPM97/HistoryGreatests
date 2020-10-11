package com.lucas.historygreatests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import com.lucas.historygreatests.models.User
import com.lucas.historygreatests.utils.AuthenticationManager

class UserViewModel: ViewModel(){

    var user = MutableLiveData<User>()

    fun authenticateWithFirebase(googleTokenId: String?) {
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        user = AuthenticationManager.loginWithFirebaseGoogle(googleAuthCredential)
    }

    fun logout() {
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