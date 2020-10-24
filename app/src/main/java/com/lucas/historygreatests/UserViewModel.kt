package com.lucas.historygreatests

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.GoogleAuthProvider
import com.lucas.historygreatests.models.User
import com.lucas.historygreatests.utils.AuthenticationManager

class UserViewModel : ViewModel() {

    var user = MutableLiveData<User>()
    var googleSignInClient = MutableLiveData<GoogleSignInClient>()

    fun initViewModel(context: Context) {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient.value = GoogleSignIn.getClient(context, gso)
        //Subscribe to Firebase AuthStateChange listener
        AuthenticationManager.authStateListener {
            if (it.currentUser == null) {
                user.value = null
                googleSignInClient.value?.signOut()
            }
        }
    }

    fun authenticateWithFirebase(googleTokenId: String?) {
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        AuthenticationManager.loginWithFirebaseGoogle(googleAuthCredential)
            .addOnCompleteListener { authTask ->
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