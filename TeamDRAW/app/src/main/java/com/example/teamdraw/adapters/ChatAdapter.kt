package com.example.teamdraw.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemChatBinding
import com.example.teamdraw.databinding.ItemChatLeftBinding
import com.example.teamdraw.models.Chat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val adapterScope = CoroutineScope(Dispatchers.Default)

class ChatAdapter() : ListAdapter<Chat, RecyclerView.ViewHolder>(ChatDiffCallback()) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    fun setList(nlist: MutableList<Chat>) {
        Log.d("size ", nlist.size.toString())
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(nlist)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) ChatViewHolder.from(parent) else ChatLeftViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChatViewHolder -> holder.bind(getItem(position))
            is ChatLeftViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).userId == auth.currentUser!!.uid) 0 else 1
    }

    class ChatViewHolder(val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            binding.chat = chat
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ChatViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatBinding.inflate(layoutInflater, parent, false)
                return ChatViewHolder(binding)
            }
        }

    }

    class ChatLeftViewHolder(val binding: ItemChatLeftBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            binding.chat = chat
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ChatLeftViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatLeftBinding.inflate(layoutInflater, parent, false)
                return ChatLeftViewHolder(binding)
            }
        }
    }

}


class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }
}
