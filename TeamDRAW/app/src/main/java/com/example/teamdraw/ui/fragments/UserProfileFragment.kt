package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentUserProfileBinding
import com.google.android.material.tabs.TabLayout

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private val args by navArgs<UserProfileFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        binding.popUpBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.user = args.user

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.sameConstraint.visibility = View.VISIBLE
                        binding.diffConstraint.visibility = View.INVISIBLE
                    }
                    1 -> {
                        binding.sameConstraint.visibility = View.INVISIBLE
                        binding.diffConstraint.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        return binding.root
    }

}