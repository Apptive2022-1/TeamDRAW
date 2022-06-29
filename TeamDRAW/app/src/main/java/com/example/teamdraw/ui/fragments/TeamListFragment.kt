package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.teamdraw.R
import com.example.teamdraw.adapters.TeamListViewPagerAdapter
import com.example.teamdraw.databinding.FragmentTeamlistBinding
import com.example.teamdraw.models.Team

import com.example.teamdraw.ui.dialog.MakeTeamDialog
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TeamListFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentTeamlistBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamlistBinding.inflate(inflater, container, false)
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTeamListViewPager()
        initButton()

    }

    private fun initButton() {
        binding.btnMakeTeam.setOnClickListener {
            val makeTeamdialog = MakeTeamDialog(requireContext())
            makeTeamdialog.show()
            makeTeamdialog.findViewById<Button>(R.id.btn_createTeam).setOnClickListener {
                val etv_input_teamName =
                    makeTeamdialog.findViewById<EditText>(R.id.etv_input_teamName)
                val etv_input_For = makeTeamdialog.findViewById<EditText>(R.id.etv_input_For)
                if (etv_input_For.text.isNotEmpty() && etv_input_teamName.text.isNotEmpty()) {
                    val team = Team(
                        makeRandomTeamID(),
                        mutableListOf(auth.currentUser?.uid.toString()),
                        etv_input_teamName.text.toString(),
                        "",
                        etv_input_For.text.toString(),auth.currentUser?.uid.toString()
                    )
                    updateTeamInfoToDB(team, makeTeamdialog)
                } else {
                    Toast.makeText(requireContext(), "정보를 모두 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }

            makeTeamdialog.findViewById<Button>(R.id.btn_cancel_makeTeam).setOnClickListener {
                makeTeamdialog.dismiss()

            }
        }
    }

    private fun updateTeamInfoToDB(team: Team, makeTeamDialog: MakeTeamDialog) {
        val db = Firebase.firestore

        val dbRef = db.collection("Teams").document(team.teamID)
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) { // document 가 존재하는 경우
                    Log.d("CreateTeam  ", "이미 존재하는 teamID" + team.teamID)
                    Toast.makeText(requireContext(), "팀 생성 실패, 이미 존재하는 teamID", Toast.LENGTH_SHORT)
                        .show()
                } else { // document.exists() = false 인 경우는, 중복되지 않은 teamID
                    db.collection("Teams").document(team.teamID)
                        .set(team)
                        .addOnSuccessListener {
                            Log.d("CreateTeam ", "팀 생성 success")
                        }
                    updateTeamData(team)
                    makeTeamDialog.dismiss()
                    initTeamListViewPager()
                }
            }
    }

    private fun updateTeamData(team: Team) {
        userInfoViewModel.addList(team.teamID, "TEAMLIST")
        val userId = auth.currentUser?.uid // userId 가져오기
        val db = Firebase.firestore
        val dbRef = db.collection("Users").document(userId.toString())
        dbRef.get()
            .addOnSuccessListener {
                db.collection("Users").document(userId.toString())
                    .update("teamList", userInfoViewModel.teamList.value)
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
    }

    private fun initTeamListViewPager(){
        val teamFragmentList = arrayListOf<Fragment>()
        val teamList = userInfoViewModel.teamList.value
        Log.d("test", teamList!!.size.toString())
        if (teamList.size != 0) {
            for (teamId in teamList){
                teamFragmentList.add(TeamFragment.newInstance(teamId))
            }

        }
        else {
            teamFragmentList.add(EmptyTeamListFragment())
        }
        val vpAdapter = TeamListViewPagerAdapter(teamFragmentList,this)
        binding.vpTeamList.adapter = vpAdapter
    }



    private fun makeRandomTeamID(): String {
        val str = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        var code = ""
        for (i in 1..10) code += str[(Math.random() * str.size).toInt()]
        return code
    }
}



