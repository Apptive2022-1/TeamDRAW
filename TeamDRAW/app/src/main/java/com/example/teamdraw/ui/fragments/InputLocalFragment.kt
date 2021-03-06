package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentInputLocalBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth

class InputLocalFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputLocalBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputLocalBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
        dataChanged()
    }

    fun dataChanged() {
        binding.btnMale.setOnClickListener {
            binding.btnFemale.setBackgroundResource(R.drawable.frame_btn_notselected)
            binding.btnMale.setBackgroundResource(R.drawable.frame_btn_selected)
            userInfoViewModel.updateValue("male","SEX")
        }
        binding.btnFemale.setOnClickListener {
            binding.btnFemale.setBackgroundResource(R.drawable.frame_btn_selected)
            binding.btnMale.setBackgroundResource(R.drawable.frame_btn_notselected)
            userInfoViewModel.updateValue("female","SEX")
        }
        binding.etvInputLocal.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputLocal.text.toString(), "LOCAL")
        }


    }

    fun initButton() {
        when (userInfoViewModel.sex?.value) {
            "male" -> {
                binding.btnMale.setBackgroundResource(R.drawable.frame_btn_selected)
                binding.btnFemale.setBackgroundResource(R.drawable.frame_btn_notselected)
            }
            "female" -> {
                binding.btnMale.setBackgroundResource(R.drawable.frame_btn_notselected)
                binding.btnFemale.setBackgroundResource(R.drawable.frame_btn_selected)
            }
            else -> {
                binding.btnMale.setBackgroundResource(R.drawable.frame_btn_notselected)
                binding.btnFemale.setBackgroundResource(R.drawable.frame_btn_notselected)
            }

        }
    }
}

