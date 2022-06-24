package com.example.teamdraw.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.databinding.FragmentInputProfileBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputProfileFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.etvInputSelfIntroduce.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputSelfIntroduce.text.toString(), "SELFINTRODUCE")
        }

        binding.etvInputPersonalLink.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputPersonalLink.text.toString(), "PERSONALLINK")
        }


    }


}