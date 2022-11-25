package com.example.binarwatchflix.pkg.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.binarwatchflix.R
import com.example.binarwatchflix.data.firebase.model.ChatMessage
import com.example.binarwatchflix.data.firebase.model.User
import com.example.binarwatchflix.databinding.ChatMessageBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseError

class ChatListAdapter(
    dataStream: FirebaseRecyclerOptions<ChatMessage>,
    private val user: User?,
    private val onDataExist: () -> Unit,
    private val onLoading: (isLoading: Boolean) -> Unit,
    private val onDataEmpty: () -> Unit,
    private val onDataError: (e: Exception) -> Unit
) : FirebaseRecyclerAdapter<ChatMessage, ChatListAdapter.ChatMessageViewHolder>(dataStream) {

    init {
        onLoading(true)
    }

    class ChatMessageViewHolder(private val binding: ChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: ChatMessage, user: User?) {
            if (user?.email?.isNullOrEmpty() == false && chat.sender?.email == user?.email) {
                binding.groupOtherUserChat.isVisible = false
                binding.tvMessageUser.isVisible = true
                binding.tvMessageUser.text = chat.message
            } else {
                binding.groupOtherUserChat.isVisible = true
                binding.tvMessageUser.isVisible = false
                binding.tvMessageOtherUser.text = chat.message
                binding.ivChatPhoto.load(chat.sender?.photoProfileUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_default_account)
                    error(R.drawable.ic_default_account)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val binding = ChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMessageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ChatMessageViewHolder,
        position: Int,
        model: ChatMessage
    ) {
        holder.bind(model, user)
    }

    override fun onDataChanged() {
        super.onDataChanged()
        onLoading(false)
        if (itemCount < 1) {
            onDataEmpty()
        } else {
            onDataExist()
        }
    }

    override fun onError(error: DatabaseError) {
        super.onError(error)
        onDataError(error.toException())
    }
}