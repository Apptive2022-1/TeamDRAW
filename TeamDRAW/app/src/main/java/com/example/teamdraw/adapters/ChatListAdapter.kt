package com.example.teamdraw.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemChatListBinding
import com.example.teamdraw.models.ChatList
import com.example.teamdraw.models.OneToOneChat
import com.example.teamdraw.models.TeamChat
import com.example.teamdraw.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ChatListAdapter() : RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {
    var clickListener: ChatListListener = ChatListListener {  }
    private var list = mutableListOf<OneToOneChat>()

    fun setList(nlist: MutableList<OneToOneChat>) {
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
        private val auth: FirebaseAuth = FirebaseAuth.getInstance()

        fun bind(chat: OneToOneChat, clickListener: ChatListListener) {
            val db = Firebase.firestore
            val dbRef = db.collection("OneToOneChat").document(chat.chatId!!)
            dbRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) { // document 가 존재하는 경우
                        var chatList = ChatList("","","","", "")
                        val chat = document.toObject<OneToOneChat>()
                        Log.d("chat  ", chat.toString())
                        for(h in chat!!.host){
                            if(h != auth.currentUser?.uid.toString()){
                                val dbRef = db.collection("Users").document(h)
                                dbRef.get().addOnSuccessListener {  document ->
                                    if(document.exists()){
                                        val user = document.toObject<User>()!!
                                        chatList.name = user.nickname!!
                                        if(chat!!.chatList.isNotEmpty()) {
                                            val lastChat = chat!!.chatList.last()
                                            chatList.message = lastChat.message
                                            chatList.time = lastChat.time
                                        }
                                        if(user.positionList!!.size >0){
                                            chatList.position = user.positionList!![0]
                                        }
                                        else{
                                            chatList.position = "포지션미정"
                                        }
                                        binding.chatList = chatList
                                        binding.clickListener = clickListener
                                        binding.executePendingBindings()
                                    }
                                }
                            }
                        }

                    }
                    else {
                    }
                }
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