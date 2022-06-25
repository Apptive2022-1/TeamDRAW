package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.RecruitRVAdapter
import com.example.teamdraw.adapters.WantingRVAdapter
import com.example.teamdraw.databinding.FragmentWantingTeamBinding


class WantingTeamFragment : Fragment() {

    lateinit var binding: FragmentWantingTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWantingTeamBinding.inflate(inflater, container, false)
        val wantingAdapter = WantingRVAdapter(object : WantingRVAdapter.ItemClickListener {
            override fun onClick() {
                findNavController().navigate(WantingTeamFragmentDirections.actionWantingTeamFragmentToUserProfileFragment())
            }
        })
        val directions = WantingTeamFragmentDirections
        wantingAdapter.setList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
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

}