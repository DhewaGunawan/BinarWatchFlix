package com.example.binarwatchflix.data.firebase

import com.example.binarwatchflix.data.firebase.model.ChatMessage
import com.example.binarwatchflix.utils.setValueAsync
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

interface ChatDataSource {
    suspend fun sendChat(chatMessage: ChatMessage): Boolean
    fun getAllChat(): FirebaseRecyclerOptions<ChatMessage>
}

class FirebaseChatDataSourceImpl(private val firebaseDatabase: FirebaseDatabase) : ChatDataSource {
    override suspend fun sendChat(chatMessage: ChatMessage): Boolean {
        return getChild().child(chatMessage.id).setValueAsync(chatMessage)
    }

    override fun getAllChat(): FirebaseRecyclerOptions<ChatMessage> {
        return FirebaseRecyclerOptions.Builder<ChatMessage>()
            .setQuery(getChild(), ChatMessage::class.java)
            .build()
    }

    private fun getChild() = firebaseDatabase.reference.child(CHATS_CHILD)

    companion object {
        private const val CHATS_CHILD = "chats"
    }
}