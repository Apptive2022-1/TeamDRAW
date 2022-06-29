package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentChattingBinding
import com.example.teamdraw.databinding.FragmentCheckTeamNoticeBinding
import com.example.teamdraw.models.Team
import com.example.teamdraw.ui.dialog.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CheckTeamNoticeFragment : Fragment() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: FragmentCheckTeamNoticeBinding
    private var teamName: String? = null
    private var teamNotice: String? = null
    private var teamLeader : String? = null
    private var teamID : String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckTeamNoticeBinding.inflate(inflater, container, false)
        arguments?.let {
            teamName = it.getString("teamName")
            teamNotice = it.getString("teamNotice")
            teamLeader = it.getString("teamLeader")
            teamID = it.getString("teamID")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Leader", teamLeader.toString())

        if(auth.currentUser?.uid.toString() != teamLeader){
            binding.etvTeamNotice.isEnabled = false
            Log.d("Leader2", teamLeader.toString())
        }
        binding.etvTeamNotice.setText(teamNotice)
        binding.tvTeamName.text = teamName
        binding.btnBack.setOnClickListener{
            val dialog = makeLoadingDialog() // Loading Dialog 호출
            dialog.show()
            if(auth.currentUser?.uid.toString() == teamLeader){
                val db = Firebase.firestore
                val dbRef = db.collection("Teams").document(teamID!!)
                dbRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) { // document 가 존재하는 경우
                            db.collection("Teams").document(teamID!!)
                                .update("teamNotice", binding.etvTeamNotice.text.toString())
                                .addOnSuccessListener {
                                    dialog.dismiss()
                                    findNavController().popBackStack()
                                }
                        }
                        else {
                        }
                    }
            }
            else {
                findNavController().popBackStack()
            }
        }







    }
    private fun makeLoadingDialog(): LoadingDialog {
        return LoadingDialog(requireContext())
    }



}