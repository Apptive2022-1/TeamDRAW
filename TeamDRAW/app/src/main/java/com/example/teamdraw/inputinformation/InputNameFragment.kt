package com.example.teamdraw.inputinformation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.databinding.FragmentInputNameBinding

class InputNameFragment : Fragment() {

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputNameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputNameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etvInputRealname.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputRealname.text.toString(), "NAME")
        }

        binding.etvInputNickname.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputNickname.text.toString(), "NICKNAME")
        }

    }



}