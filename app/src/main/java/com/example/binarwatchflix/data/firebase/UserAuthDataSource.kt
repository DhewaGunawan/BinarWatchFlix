package com.example.binarwatchflix.data.firebase

import com.example.binarwatchflix.data.firebase.model.User
import com.example.binarwatchflix.utils.toUserObject
import com.google.firebase.auth.FirebaseAuth

interface UserAuthDataSource {
    fun getCurrentUser(): User?
}

class FirebaseUserAuthDataSourceImpl(private val firebaseAuth: FirebaseAuth) : UserAuthDataSource {
    override fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.toUserObject()
    }

}