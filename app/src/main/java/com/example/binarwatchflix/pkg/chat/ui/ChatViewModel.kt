package com.example.binarwatchflix.pkg.chat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.binarwatchflix.data.repository.ChatRepository
import com.example.binarwatchflix.data.repository.UserRepository
import com.example.binarwatchflix.wrapper.Resource
import androidx.lifecycle.viewModelScope
import com.example.binarwatchflix.data.firebase.model.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val sendChatResult = MutableLiveData<Resource<Boolean>>()

    fun sendChat(message: String) {
        sendChatResult.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            sendChatResult.postValue(
                chatRepository.sendChat(generateChatMessage(message))
            )
        }
    }

    private fun generateChatMessage(message: String): ChatMessage {
        return ChatMessage(sender = userRepository.getCurrentUser(), message = message)
    }
}