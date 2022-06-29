package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.ChatListAdapter
import com.example.teamdraw.adapters.ChatListListener
import com.example.teamdraw.databinding.FragmentChattingListBinding
import com.example.teamdraw.models.ChatList
import com.example.teamdraw.models.OneToOneChat
import com.example.teamdraw.models.User
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class ChattingListFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: FragmentChattingListBinding
    private lateinit var adapter : ChatListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChattingListBinding.inflate(inflater, container, false)

        adapter = ChatListAdapter()
        adapter.clickListener = ChatListListener {
            Log.d("te ", "qfqfqwf")
        }
        adapter.setList(mutableListOf())

        binding.chatListRecyclerview.adapter = adapter
        binding.chatListRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        var chatList = mutableListOf<OneToOneChat>()
        val db = Firebase.firestore
        val dbRef = db.collection("Users").document(auth.currentUser!!.uid)
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) { // document 가 존재하는 경우
                    val one_to_one_chatList = document.toObject<User>()!!.one_to_one_ChatList
                    if (one_to_one_chatList != null) {
                        if(one_to_one_chatList.size > 0){
                            for(chatId in one_to_one_chatList!!){
                                val db = Firebase.firestore
                                val dbRef = db.collection("OneToOneChat").document(chatId)
                                dbRef.get()
                                    .addOnSuccessListener { document ->
                                        if (document.exists()) { // document 가 존재하는 경우
                                            val chat = document.toObject<OneToOneChat>()
                                            chatList.add(chat!!)
                                            adapter.setList(chatList)
                                            adapter.clickListener = ChatListListener {
                                                var bundle = Bundle()
                                                bundle.putString("onetooneChatID",chatId)
                                                findNavController().navigate(R.id.action_chattingListFragment_to_oneToOneChat, bundle)
                                            }
                                        } else {
                                        }
                                    }

                            }
                        }
                    }
                }

            }
    }

}