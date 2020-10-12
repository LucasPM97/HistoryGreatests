package com.lucas.historygreatests.utils

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lucas.historygreatests.models.User

class AuthenticationManager private constructor() {

    private val firebaseAuth = Firebase.auth

    private object HOLDER {
        val INSTANCE = AuthenticationManager()
    }

    companion object {
        
        private val instance: AuthenticationManager by lazy { HOLDER.INSTANCE }


        fun isAuthenticated() = instance.firebaseAuth.currentUser != null

        fun getUser() = instance.firebaseAuth.currentUser

        fun mapFirebaseUser(firebaseUser: FirebaseUser) = User(
            uid = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email
        )

        fun loginWithFirebaseGoogle(googleAuthCredential: AuthCredential): Task<AuthResult> {
            return instance.firebaseAuth.signInWithCredential(googleAuthCredential)
        }

        fun logout() {
            instance.firebaseAuth.signOut()
        }

        fun authStateListener(listener: FirebaseAuth.AuthStateListener) = instance.firebaseAuth.addAuthStateListener(listener)
    }
}