package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentOneToOneChatBinding
import com.example.teamdraw.models.Chat
import com.example.teamdraw.models.OneToOneChat
import com.example.teamdraw.models.TeamChat
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

class OneToOneChatFragment : Fragment() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentOneToOneChatBinding

    private lateinit var adapter: ChatAdapter
    private var onetooneChatID: String? = null
    private var onetooneChat = mutableListOf<Chat>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOneToOneChatBinding.inflate(inflater, container, false)
        adapter = ChatAdapter()

        arguments?.let {
            onetooneChatID = it.getString("onetooneChatID")
        }

        adapter.setList(initChattingList())
        binding.chatRecyclerview2.adapter = adapter
        binding.chatRecyclerview2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageButton()
        observeChattingLog()
    }

    private fun sendMessageButton() {
        binding.btnSendMessage.setOnClickListener {
            if (binding.etvInputMessage.text.isNotEmpty()) {
                val chatlog = Chat(
                    binding.etvInputMessage.text.toString(), getTime(), auth.currentUser!!.uid,
                    userInfoViewModel.nickname.value.toString()
                )
                onetooneChat.add(chatlog)
                val db = Firebase.firestore
                val dbRef = db.collection("OneToOneChat").document(onetooneChatID!!)
                dbRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) { // document 가 존재하는 경우
                            db.collection("OneToOneChat").document(onetooneChatID!!)
                                .update("chatList", onetooneChat)
                                .addOnSuccessListener {
                                    Log.d("챝잉전송", "완료")
                                    binding.etvInputMessage.text.clear()

                                }
                        } else {
                        }
                    }
            }
        }
    }

    private fun getTime(): String {
        val current = System.currentTimeMillis()
        val d = SimpleDateFormat("yyyy.MM.dd")
        val t = SimpleDateFormat("hh:mm a")
        val day = d.format(current)
        val time = t.format(current)
        return day + "\n" + time
    }

    private fun observeChattingLog() {
        val db = Firebase.firestore
        Log.d("onetooneChat 변경감지", onetooneChatID.toString())
        val dbRef = db.collection("OneToOneChat").document(onetooneChatID!!)
        dbRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("error", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                onetooneChat = snapshot!!.toObject<OneToOneChat>()!!.chatList
                adapter.setList(onetooneChat)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initChattingList(): MutableList<Chat> {
        val db = Firebase.firestore
        val dbRef = db.collection("OneToOneChat").document(onetooneChatID.toString())
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    onetooneChat = document.toObject<OneToOneChat>()!!.chatList
                    adapter.setList(onetooneChat)
                    adapter.notifyDataSetChanged()
                    Log.d("data ", document.toObject<TeamChat>()!!.chatList.toString())
                } else {
                    Log.d("OnetoOne Chatting", "최초 생성")
                }

            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }
        return onetooneChat
    }


}