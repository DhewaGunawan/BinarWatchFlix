package com.example.binarwatchflix.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.example.binarwatchflix.pkg.chat.adapter.ChatListAdapter

class OnReplyScrollObserver(
    private val recyclerView: RecyclerView,
    private val adapter: ChatListAdapter
) : AdapterDataObserver() {

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        val count = adapter.itemCount
        recyclerView.smoothScrollToPosition(count - 1)
    }
}