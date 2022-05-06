package com.example.teamdraw.findingteammember

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentFindingTeamMembersBinding

class FindingTeamMembersFragment : Fragment() {

    private lateinit var binding: FragmentFindingTeamMembersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFindingTeamMembersBinding.inflate(
            inflater,
            container,
            false
        )

        val recruitAdapter = RecruitRVAdapter()
        val wantingAdapter = WantingRVAdapter()
        recruitAdapter.setList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        wantingAdapter.setList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        binding.apply {
            with(recruitRv) {
                adapter = recruitAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
            with(wantingRv) {
                adapter = wantingAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
        return binding.root
    }
}