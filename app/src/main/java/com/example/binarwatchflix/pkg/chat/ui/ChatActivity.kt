package com.example.binarwatchflix.pkg.chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.binarwatchflix.base.BaseViewModelActivity
import com.example.binarwatchflix.databinding.ActivityChatBinding
import com.example.binarwatchflix.pkg.chat.adapter.ChatListAdapter
import com.example.binarwatchflix.wrapper.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity :
    BaseViewModelActivity<ActivityChatBinding, ChatViewModel>(ActivityChatBinding::inflate) {

    override val viewModel: ChatViewModel by viewModel()

    private val adapter: ChatListAdapter by lazy {
        ChatListAdapter(
            viewModel.getAllChat(),
            viewModel.getCurrentUser(),
            onDataExist = {
                showData()
            },
            onLoading = {
                showLoading(it)
            },
            onDataError = {
                showError()
                setErrorMessage(it.message.orEmpty())
            },
            onDataEmpty = {
                showEmptyData()
            })
    }

    private fun showData() {
        binding.groupEmptyChat.isVisible = false
        binding.rvChatMessages.isVisible = true
        println("showData")
    }

    private fun showLoading(isLoading: Boolean) {
        binding.groupEmptyChat.isVisible = isLoading
        binding.rvChatMessages.isVisible = false
        binding.tvEmptyChatTitle.text = "Loading..."
        binding.tvEmptyChatDescription.text = ""
    }

    private fun showError() {
        binding.groupEmptyChat.isVisible = true
        binding.rvChatMessages.isVisible = false
    }

    private fun setErrorMessage(message: String) {
        binding.tvEmptyChatDescription.text = message
        binding.rvChatMessages.isVisible = false
        println("Error getting list chat $message")
    }

    private fun showEmptyData() {
        binding.groupEmptyChat.isVisible = true
        binding.rvChatMessages.isVisible = false
        binding.tvEmptyChatTitle.text = "Sorry"
        binding.tvEmptyChatDescription.text = "Let's start chatting"
        println("No chat :(")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        initView()
        observeData()
    }

    fun initView() {
        binding.cvButtonSend.setOnClickListener {
            sendChatMessage()
        }
        binding.rvChatMessages.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        adapter.stopListening()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    fun observeData() {
        viewModel.sendChatResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    // do nothing
                }
                is Resource.Error -> {
                    println("Error ${it.exception}")
                    Toast.makeText(this@ChatActivity, "Error to send chat :(", Toast.LENGTH_LONG)
                        .show()
                }
                is Resource.Loading -> {
                    // do nothing
                }
                is Resource.Success -> {
                    binding.etChat.setText("")
                }
            }
        }
    }

    private fun sendChatMessage() {
        val message = binding.etChat.text.toString()
        if (message.isNotEmpty()) {
            viewModel.sendChat(message)
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }
}