package com.example.binarwatchflix.pkg.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.binarwatchflix.data.firebase.UserRepository
import com.example.binarwatchflix.data.firebase.model.User

class SplashViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    val currentUserLiveData = MutableLiveData<User?>()

    fun getCurrentUser() {
        currentUserLiveData.postValue(userRepository.getCurrentUser())
    }
}