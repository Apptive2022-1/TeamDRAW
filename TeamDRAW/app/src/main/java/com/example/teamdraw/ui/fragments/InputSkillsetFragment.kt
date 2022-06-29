package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragementInputSkillsetBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel


class InputSkillsetFragment : Fragment(), View.OnClickListener{

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragementInputSkillsetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragementInputSkillsetBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSetOnClickListener()
        initButton()
    }

    private fun initButton() {
        val skillMS = binding.constraintLayoutSkillMS.children
        val skillL = binding.constraintLayoutSkillL.children
        val skillD = binding.constraintLayoutSkillD.children
        val skillP = binding.constraintLayoutSkillP.children


        skillMS.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.skillList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        skillL.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.skillList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        skillD.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.skillList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        skillP.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.skillList.value!!.contains(button!!.text.toString())){
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else {
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }


    }
    private fun btnSetOnClickListener() {
        val skillMS = binding.constraintLayoutSkillMS.children
        skillMS.forEach { btn->
            btn.setOnClickListener(this)
        }
        val skillL = binding.constraintLayoutSkillL.children
        skillL.forEach { btn->
            btn.setOnClickListener(this)
        }
        val skillD = binding.constraintLayoutSkillD.children
        skillD.forEach { btn->
            btn.setOnClickListener(this)
        }
        val skillP = binding.constraintLayoutSkillP.children
        skillP.forEach { btn->
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(btn: View?) {
        val button = view?.findViewById<Button>(btn!!.id)
        if (userInfoViewModel.skillList.value!!.contains(button!!.text.toString())) {
            btn!!.setBackgroundResource(R.drawable.frame_btn_notselected2)
            userInfoViewModel.removeList(button!!.text.toString(), "SKILL")
        } else {
            btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
            userInfoViewModel.addList(button!!.text.toString(), "SKILL")
        }
    }

}