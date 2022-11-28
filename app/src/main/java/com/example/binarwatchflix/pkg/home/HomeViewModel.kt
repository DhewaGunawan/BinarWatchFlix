package com.example.binarwatchflix.pkg.home

import androidx.lifecycle.ViewModel
import com.example.binarwatchflix.data.firebase.UserRepository

class HomeViewModel (
    private val userRepository: UserRepository
) : ViewModel() {

    fun doLogout() {
        userRepository.logoutUser()
    }
}