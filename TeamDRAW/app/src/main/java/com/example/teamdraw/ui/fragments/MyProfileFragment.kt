package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentMyProfileBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel


class MyProfileFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView14.setOnClickListener{
            findNavController().navigate(R.id.action_myProfileFragment_to_inputInformationFragment)
        }
        binding.userNameTv.text = userInfoViewModel.nickname.value
        if(userInfoViewModel.positionList.value!!.size > 0){
            binding.tv1.text = userInfoViewModel.positionList.value!![0]
        }
        else{
            binding.tv1.text = "포지션미정"
        }
        binding.tvSelf.text = userInfoViewModel.selfItroduce.value + "\n\n\n" + userInfoViewModel.personalLink.value
    }
}