package com.example.teamdraw.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentChattingBinding
import com.example.teamdraw.models.Chat
import com.example.teamdraw.models.Team
import com.example.teamdraw.models.TeamChat
import com.example.teamdraw.models.User
import com.example.teamdraw.ui.dialog.MakeTeamDialog
import com.example.teamdraw.ui.dialog.SelectEvaluateMemberDialog
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
    private var teamChat = mutableListOf<Chat>()
    private var teamName: String? = null
    private var teamNotice: String? = null
    private var teamLeader : String? = null


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
            teamNotice = it.getString("teamNotice")
            teamLeader = it.getString("teamLeader")
        }
        adapter.setList(initChattingList())
        binding.chatRecyclerview.adapter = adapter
        binding.chatRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        navigationView = binding.navigationView
        drawerLayout = binding.drawLayout

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_evaluate -> {
                    //val selectMember = SelectEvaluateMemberDialog(requireContext())
                    val selectMember = Dialog(requireContext())
                    selectMember.setContentView(R.layout.dialog_select_evaluate_member)
                    val params : WindowManager.LayoutParams  = selectMember.window!!.attributes
                    params.width = WindowManager.LayoutParams.MATCH_PARENT
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT
                    selectMember.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경 투명하게
                    selectMember.window!!.attributes = params
                    selectMember.show()

                    selectMember.findViewById<TextView>(R.id.tv_jiwoo).setOnClickListener {
                        var bundle = Bundle()
                        bundle.putString("name", "지우개")
                        bundle.putString("teamName", teamName)
                        findNavController().navigate(R.id.action_chattingFragment_to_memberEvaluateFragment,bundle)
                        selectMember.dismiss()
                    }
                    selectMember.findViewById<TextView>(R.id.tv_jeong).setOnClickListener {
                        var bundle = Bundle()
                        bundle.putString("name", "킹갓더정헌")
                        bundle.putString("teamName", teamName)
                        findNavController().navigate(R.id.action_chattingFragment_to_memberEvaluateFragment,bundle)
                        selectMember.dismiss()
                    }
                    selectMember.findViewById<TextView>(R.id.tv_yea).setOnClickListener {
                        var bundle = Bundle()
                        bundle.putString("name", "계진")
                        bundle.putString("teamName", teamName)
                        findNavController().navigate(R.id.action_chattingFragment_to_memberEvaluateFragment,bundle)
                        selectMember.dismiss()
                    }





                }
                R.id.btn_exitTeam -> {
                    val db = Firebase.firestore
                    val dbRef = db.collection("Users").document(auth.currentUser?.uid.toString())
                    dbRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) { // document 가 존재하는 경우
                                val teamList = document.toObject<User>()!!.teamList
                                teamList!!.remove(teamID)
                                userInfoViewModel.teamList.value = teamList
                                dbRef.get()
                                    .addOnSuccessListener {
                                        db.collection("Users").document(auth.currentUser?.uid.toString())
                                            .update("teamList", teamList)
                                            .addOnSuccessListener {
                                                Log.d("DB Update ", "success")
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(context, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                                            }

                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "인터넷 연결이 불안정합니다. 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                            }
                        }




                }
                R.id.btn_checkNotice -> {
                    var bundle = Bundle()
                    bundle.putString("teamName", teamName)
                    bundle.putString("teamNotice", teamNotice)
                    bundle.putString("teamLeader", teamLeader)
                    bundle.putString("teamID", teamID)
                    findNavController().navigate(R.id.action_chattingFragment_to_checkTeamNoticeFragment,bundle)
                }
            }
            drawerLayout.closeDrawer(navigationView)
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