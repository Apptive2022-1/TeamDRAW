package com.example.teamdraw.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentContestDetailBinding


class ContestDetailFragment : Fragment() {

    private val args by navArgs<ContestDetailFragmentArgs>()
    lateinit var binding: FragmentContestDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContestDetailBinding.inflate(inflater, container, false)
        binding.contest = args.contest
        binding.frameBtn.setOnClickListener {
            findNavController().navigate(
                ContestDetailFragmentDirections
                    .actionContestDetailFragmentToFindingTeamMembersFragment()
            )
        }

        binding.heartBtn.setOnClickListener {
            binding.heartBtn.setColorFilter(Color.parseColor("#AB78FF"), PorterDuff.Mode.SRC_IN)
        }

        binding.popUpBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}