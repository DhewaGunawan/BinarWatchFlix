package com.example.binarwatchflix.pkg.chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.binarwatchflix.base.BaseViewModelActivity
import com.example.binarwatchflix.databinding.ActivityChatBinding
import com.example.binarwatchflix.wrapper.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity :
    BaseViewModelActivity<ActivityChatBinding, ChatViewModel>(ActivityChatBinding::inflate) {

    override val viewModel: ChatViewModel by viewModel()

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
    }

    fun observeData() {
        viewModel.sendChatResult.observe(this) {
            when (it) {
                is Resource.Empty -> {
                    Toast.makeText(this@ChatActivity, "Empty data :(", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    println("Error ${it.exception}")
                    Toast.makeText(this@ChatActivity, "Error to send chat :(", Toast.LENGTH_LONG)
                        .show()
                }
                is Resource.Loading -> {
                    Toast.makeText(this@ChatActivity, "Loading...", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    println("it success ${it.toString()}")
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