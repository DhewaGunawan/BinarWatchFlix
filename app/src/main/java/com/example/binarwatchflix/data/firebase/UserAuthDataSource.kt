package com.example.binarwatchflix.data.firebase

import com.example.binarwatchflix.data.firebase.model.User
import com.example.binarwatchflix.utils.await
import com.example.binarwatchflix.utils.toUserObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

interface UserAuthDataSource {
    suspend fun signInWithCredential(token: String): Pair<Boolean, User?>
    fun getCurrentUser(): User?
    fun logoutUser()
}

class FirebaseUserAuthDataSourceImpl(private val firebaseAuth: FirebaseAuth) :
    UserAuthDataSource {

    override suspend fun signInWithCredential(token: String): Pair<Boolean, User?> {
        val credential = GoogleAuthProvider.getCredential(token, null)
        val result = firebaseAuth.signInWithCredential(credential).await()
        return if (result != null) {
            Pair(true, firebaseAuth.currentUser?.toUserObject())
        } else {
            Pair(false, null)
        }
    }

    override fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.toUserObject()
    }

    override fun logoutUser() {
        firebaseAuth.signOut()
    }

}