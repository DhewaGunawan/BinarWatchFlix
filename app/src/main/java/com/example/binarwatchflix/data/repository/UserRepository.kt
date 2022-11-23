package com.example.binarwatchflix.data.repository

import com.example.binarwatchflix.base.BaseRepository
import com.example.binarwatchflix.data.firebase.UserAuthDataSource
import com.example.binarwatchflix.data.firebase.model.User

interface UserRepository {
    fun getCurrentUser(): User?
}

class UserRepositoryImpl(private val userAuthDataSource: UserAuthDataSource) : BaseRepository(),
    UserRepository {
    override fun getCurrentUser(): User? {
        return userAuthDataSource.getCurrentUser()
    }

}