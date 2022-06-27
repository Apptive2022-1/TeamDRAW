package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentTeamBinding
import com.example.teamdraw.models.Team
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class TeamFragment : Fragment() {
    private lateinit var binding: FragmentTeamBinding
    private var teamID: String? = null
    private var teamName: String? = null

    companion object {
        fun newInstance(teamID: String) =
            TeamFragment().apply {
                arguments = Bundle().apply {
                    putString("teamID", teamID)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teamID = it.getString("teamID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTeamData()
        binding.teamFragment.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("teamID",teamID)
            bundle.putString("teamName", teamName)
            findNavController().navigate(R.id.action_teamListFragment_to_chattingFragment,bundle)
        }


    }
    private fun setTeamData() {
        val db = Firebase.firestore
        val dbRef = db.collection("Teams").document(teamID.toString())
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val teamData = document.toObject<Team>()
                    binding.tvTeamName.text = teamData!!.teamName
                    teamName = teamData!!.teamName
                    binding.tvFor.text = teamData!!.forTarget
                    binding.tvTeamNotice.text = teamData.teamNotice

                } else {
                    Log.d("db get", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }
    }
}