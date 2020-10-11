package com.lucas.historygreatests.utils

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
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

        fun loginWithFirebaseGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User> {
            val authenticatedUserMutableLiveData = MutableLiveData<User>()
            instance.firebaseAuth.signInWithCredential(googleAuthCredential).addOnCompleteListener{ authTask->
                if (authTask.isSuccessful) {
                    val isNewUser = authTask.result?.additionalUserInfo?.isNewUser
                    getUser()?.let {
                        authenticatedUserMutableLiveData.value = mapFirebaseUser(it)
                    }
                }
            }
            return authenticatedUserMutableLiveData
        }

        fun logout() {
            instance.firebaseAuth.signOut()
        }
    }
}