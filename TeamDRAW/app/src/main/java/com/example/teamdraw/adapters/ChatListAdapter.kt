package com.example.teamdraw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemChatListBinding
import com.example.teamdraw.models.ChatList

class ChatListAdapter(private val clickListener: ChatListListener) : RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {

    private var list = listOf<ChatList>()

    fun setList(nlist: List<ChatList>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return ChatListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    override fun getItemCount(): Int = list.size

    class ChatListViewHolder(val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatList: ChatList, clickListener: ChatListListener) {
            binding.chatList = chatList
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ChatListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatListBinding.inflate(layoutInflater, parent, false)
                return ChatListAdapter.ChatListViewHolder(binding)
            }
        }
    }
}

class ChatListListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}