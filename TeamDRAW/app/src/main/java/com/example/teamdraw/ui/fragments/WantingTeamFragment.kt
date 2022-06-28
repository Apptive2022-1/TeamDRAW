package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.RecruitRVAdapter
import com.example.teamdraw.adapters.WantingRVAdapter
import com.example.teamdraw.databinding.FragmentWantingTeamBinding
import com.example.teamdraw.util.FragmentLocation
import com.example.teamdraw.viewmodels.FindingTeamViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WantingTeamFragment : Fragment() {

    lateinit var binding: FragmentWantingTeamBinding
    private lateinit var wantingAdapter: WantingRVAdapter
    private val viewModel: FindingTeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWantingTeamBinding.inflate(inflater, container, false)

        readDatabase()

        val directions = WantingTeamFragmentDirections
        binding.apply {

            wantingRv1.adapter = wantingAdapter
            wantingRv1.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            wantingBtn1.setOnClickListener {
                findNavController().navigate(
                    directions.actionWantingTeamFragmentToWantingTeamFragmentDetail(
                        "me"
                    )
                )
            }

            wantingRv2.adapter = wantingAdapter
            wantingRv2.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            wantingBtn2.setOnClickListener {
                findNavController().navigate(
                    directions.actionWantingTeamFragmentToWantingTeamFragmentDetail(
                        "different"
                    )
                )
            }

            wantingRv3.adapter = wantingAdapter
            wantingRv3.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            wantingBtn3.setOnClickListener {
                findNavController().navigate(
                    directions.actionWantingTeamFragmentToWantingTeamFragmentDetail(
                        "same"
                    )
                )
            }
        }
        return binding.root
    }
    private fun readDatabase() {
        wantingAdapter = WantingRVAdapter(FragmentLocation.Wanting)
        lifecycleScope.launch {
            viewModel.readUsers.observe(viewLifecycleOwner) { uesrs ->
                if (uesrs.isNotEmpty()) {
                    wantingAdapter.setList(uesrs)
                }
            }
        }
    }
}