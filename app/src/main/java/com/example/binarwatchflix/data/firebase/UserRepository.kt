package com.example.binarwatchflix.data.firebase

import com.example.binarwatchflix.base.BaseRepository
import com.example.binarwatchflix.data.firebase.model.User
import com.example.binarwatchflix.wrapper.Resource

interface UserRepository {
    suspend fun signInWithCredential(token: String): Resource<Pair<Boolean, User?>>
    fun getCurrentUser(): User?
    fun logoutUser()
}

class UserRepositoryImpl(
    private val userAuthDataSource: UserAuthDataSource,
) : UserRepository, BaseRepository() {

    override suspend fun signInWithCredential(token: String): Resource<Pair<Boolean, User?>> {
        return proceed { userAuthDataSource.signInWithCredential(token) }
    }

    override fun getCurrentUser(): User? {
        return userAuthDataSource.getCurrentUser()
    }

    override fun logoutUser() {
        return userAuthDataSource.logoutUser()
    }

}