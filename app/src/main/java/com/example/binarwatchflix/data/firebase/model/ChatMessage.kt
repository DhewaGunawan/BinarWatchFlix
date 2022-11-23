package com.example.binarwatchflix.data.firebase.model

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class ChatMessage(
    val id: String = "${System.currentTimeMillis()}-${UUID.randomUUID()}",
    val sender: User? = null,
    val message: String = ""
)