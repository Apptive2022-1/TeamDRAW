package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentFindingTeamMembersBinding
import com.example.teamdraw.adapters.RecruitRVAdapter
import com.example.teamdraw.adapters.WantingRVAdapter
import com.example.teamdraw.databinding.ItemRecruitBinding
import com.example.teamdraw.models.Recruiting
import com.example.teamdraw.models.User
import com.example.teamdraw.ui.dialog.RecruitingDialog
import com.example.teamdraw.ui.dialog.RecruitingDialogInterface
import com.example.teamdraw.util.FragmentLocation
import com.example.teamdraw.viewmodels.ContestsViewModel
import com.example.teamdraw.viewmodels.FindingTeamViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FindingTeamMembersFragment : Fragment(), RecruitingDialogInterface {

    private lateinit var binding: FragmentFindingTeamMembersBinding
    private lateinit var recruitAdapter: RecruitRVAdapter
    private lateinit var wantingAdapter: WantingRVAdapter
    private val viewModel: FindingTeamViewModel by viewModels()
    private var recruitingList = mutableListOf<Recruiting>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindingTeamMembersBinding.inflate(
            inflater,
            container,
            false
        )

        binding.lifecycleOwner = this
        readDatabase()
        recruitAdapter = RecruitRVAdapter(object : RecruitRVAdapter.ItemClickListener {
            override fun onClick(n: Int) {
                val dialog = RecruitingDialog(this@FindingTeamMembersFragment, recruitingList[n])
                dialog.show(activity?.supportFragmentManager!!, "Confirm")

            }
        })

        recruitAdapter.setList(recruitingList)
        binding.apply {
            with(recruitRv) {
                adapter = recruitAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            with(wantingRv1) {
                adapter = wantingAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            iv1.setOnClickListener {
                findNavController().navigate(FindingTeamMembersFragmentDirections.actionFindingTeamMembersFragmentToWantingTeamFragment())
            }
            iv2.setOnClickListener {
                findNavController().navigate(FindingTeamMembersFragmentDirections.actionFindingTeamMembersFragmentToWriteRecruitingFragment())
            }
        }

        return binding.root
    }

    private fun readDatabase() {
        wantingAdapter = WantingRVAdapter(FragmentLocation.Finding)
        lifecycleScope.launch {
            viewModel.readUsers.observe(viewLifecycleOwner) { uesrs ->
                if (uesrs.isNotEmpty()) {
                    wantingAdapter.setList(uesrs)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecruitingList()

    }

    private fun getRecruitingList() {
        recruitingList = mutableListOf()
        val db = Firebase.firestore
        val dbRef = db.collection("Recruiting")
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (doc in document) {
                        val recruiting = doc.toObject<Recruiting>()
                        recruitingList.add(recruiting)
                    }
                    recruitAdapter.setList(recruitingList)
                } else {
                    Log.d("db get", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }


    }

    override fun onYesButtonClick(id: Int) {

    }
}