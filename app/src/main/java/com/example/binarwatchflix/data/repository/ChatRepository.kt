package com.example.binarwatchflix.data.repository

import com.example.binarwatchflix.base.BaseRepository
import com.example.binarwatchflix.data.firebase.ChatDataSource
import com.example.binarwatchflix.data.firebase.model.ChatMessage
import com.example.binarwatchflix.wrapper.Resource
import com.firebase.ui.database.FirebaseRecyclerOptions

interface ChatRepository {
    suspend fun sendChat(chatMessage: ChatMessage): Resource<Boolean>
    fun getAllChat(): FirebaseRecyclerOptions<ChatMessage>
}

class ChatRepositoryImpl(private val dataSource: ChatDataSource) : BaseRepository(),
    ChatRepository {
    override suspend fun sendChat(chatMessage: ChatMessage): Resource<Boolean> {
        return proceed { dataSource.sendChat(chatMessage) }
    }

    override fun getAllChat(): FirebaseRecyclerOptions<ChatMessage> {
        return dataSource.getAllChat()
    }

}