package com.example.binarwatchflix.pkg.chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.binarwatchflix.R
import com.example.binarwatchflix.base.BaseActivity
import com.example.binarwatchflix.databinding.ActivityChatBinding

class ChatActivity : BaseActivity<ActivityChatBinding>(ActivityChatBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }
}