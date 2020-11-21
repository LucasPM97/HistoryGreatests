package com.lucas.historygreatests.repositories.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class FirestoreDatabase (
    val db: FirebaseFirestore = Firebase.firestore
){
}