package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentOneToOneChatBinding
import com.example.teamdraw.models.*
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
    private var invited : String? = null
    private var teamID : String? = null


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
            invited = it.getString("Invite")
            teamID = it.getString("teamID")
        }

        adapter.setList(initChattingList())
        binding.chatRecyclerview2.adapter = adapter
        binding.chatRecyclerview2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.popUpBtn.setOnClickListener {
            findNavController().popBackStack()
        }

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
                    if(invited != null){
                        onetooneChat.add(Chat("당신을 우리팀에 초대합니다 !!",
                            getTime(),
                            auth.currentUser?.uid.toString(),
                            userInfoViewModel.nickname.value!!))
                        val dbRef = db.collection("OneToOneChat").document(onetooneChatID.toString())
                        dbRef.get()
                            .addOnSuccessListener { document ->
                                dbRef.update("chatList", onetooneChat).addOnSuccessListener {

                                    val host = document.toObject<OneToOneChat>()!!.host
                                    for(h in host){
                                        if(h != auth.currentUser?.uid.toString()){
                                            Log.d("h ", h)
                                            val dbRef = db.collection("Users").document(h)
                                            dbRef.get()
                                                .addOnSuccessListener { document ->
                                                    var teamList = document.toObject<User>()!!.teamList
                                                    teamList!!.add(teamID.toString())
                                                    dbRef.update("teamList", teamList).addOnSuccessListener {

                                                        val dbRef = db.collection("Teams").document(teamID.toString())
                                                        dbRef.get()
                                                            .addOnSuccessListener { document ->
                                                                var memberList = document.toObject<Team>()!!.userList
                                                                memberList!!.add(h)
                                                                dbRef.update("userList", memberList)
                                                            }
                                                    }

                                                }





                                        }
                                    }
                                }
                            }
                    }
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