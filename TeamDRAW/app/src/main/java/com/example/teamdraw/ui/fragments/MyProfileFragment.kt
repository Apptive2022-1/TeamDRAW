package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentMyProfileBinding


class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        binding.textView14.setOnClickListener {
            findNavController().navigate(MyProfileFragmentDirections.actionMyProfileFragmentToInputInformationFragment())
        }
        return binding.root
    }

}