package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentChattingBinding
import com.example.teamdraw.models.Chat
import com.example.teamdraw.models.TeamChat
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat


class ChattingFragment : Fragment() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentChattingBinding

    private lateinit var adapter: ChatAdapter
    private var teamID: String? = null
    private var teamName: String? = null
    private var teamChat = mutableListOf<Chat>()


    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChattingBinding.inflate(inflater, container, false)
        adapter = ChatAdapter()
        arguments?.let {
            teamID = it.getString("teamID")
            teamName = it.getString("teamName")
        }
        adapter.setList(initChattingList())
        binding.chatRecyclerview.adapter = adapter
        binding.chatRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        navigationView = binding.navigationView
        drawerLayout = binding.drawLayout

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item1 -> {}
                R.id.item2 -> {}
                R.id.item3 -> {}
                R.id.item4 -> {}
                R.id.item5 -> {}
            }
            return@setNavigationItemSelectedListener true
        }


        binding.hamburgerBtn.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessageButton()
        observeChattingLog()
        binding.title.text = teamName
    }

    private fun sendMessageButton() {
        binding.btnSendMessage.setOnClickListener {
            if (binding.etvInputMessage.text.isNotEmpty()) {
                val chatlog = Chat(
                    binding.etvInputMessage.text.toString(), getTime(), auth.currentUser!!.uid,
                    userInfoViewModel.nickname.value.toString()
                )
                teamChat.add(chatlog)
                val db = Firebase.firestore
                val dbRef = db.collection("TeamChat").document(teamID!!)
                dbRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) { // document 가 존재하는 경우
                            db.collection("TeamChat").document(teamID!!)
                                .update("chatList", teamChat)
                                .addOnSuccessListener {
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
        Log.d("teamid", teamID.toString())
        val dbRef = db.collection("TeamChat").document(teamID.toString())
        dbRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("error", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d("changed chat ", snapshot!!.toObject<TeamChat>()!!.chatList.toString())
                teamChat = snapshot!!.toObject<TeamChat>()!!.chatList
                adapter.setList(teamChat)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initChattingList(): MutableList<Chat> {
        val db = Firebase.firestore
        Log.d("teamid", teamID.toString())
        val dbRef = db.collection("TeamChat").document(teamID.toString())
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    teamChat = document.toObject<TeamChat>()!!.chatList
                    adapter.setList(teamChat)
                    adapter.notifyDataSetChanged()
                    Log.d("data ", document.toObject<TeamChat>()!!.chatList.toString())
                } else {
                    Log.d("Team Chatting", "팀채팅 최초 생성")
                    db.collection("TeamChat").document(teamID!!)
                        .set(TeamChat(teamID!!, mutableListOf<Chat>()))
                        .addOnSuccessListener {
                            Log.d("Team Chatting", "팀채팅 최초 생성 성공")
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }
        return teamChat
    }


}