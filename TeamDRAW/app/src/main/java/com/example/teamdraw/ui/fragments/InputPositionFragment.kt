package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentInputPositionBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputPositionFragment : Fragment(), View.OnClickListener{

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputPositionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputPositionBinding.inflate(inflater, container, false)
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
        val btnDepartureList = binding.constraintLayoutBtnDepart.children
        val btnPositionList = binding.constraintLayoutBtnPosition.children
        btnDepartureList.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.departureList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        btnPositionList.forEach { btn ->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.positionList.value!!.contains(button!!.text.toString())){ // 해당 포지션 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 포지션 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
    }

    private fun btnSetOnClickListener() {
        val btnDepartureList = binding.constraintLayoutBtnDepart.children
        btnDepartureList.forEach { btn->
            btn.setOnClickListener(this)
        }
        val btnPositionList = binding.constraintLayoutBtnPosition.children
        btnPositionList.forEach {btn ->
            btn.setOnClickListener(this)
        }

    }

    override fun onClick(btn: View?) {
        val button = view?.findViewById<Button>(btn!!.id)
        if(btn!!.parent == view?.findViewById<ConstraintLayout>(R.id.constraintLayout_btnPosition) ){ // position 부분
            Log.d("id ", "position")
            if (userInfoViewModel.positionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택 해제
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
                userInfoViewModel.removeList(button!!.text.toString(),"POSITION")
            }
            else { // 해당 포지션을 선택
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
                userInfoViewModel.addList(button!!.text.toString(),"POSITION")
            }
        }
        else { // departure 부분
            Log.d("id ", "departure")
            if (userInfoViewModel.departureList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택 해제
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
                userInfoViewModel.removeList(button!!.text.toString(),"DEPARTURE")
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
                userInfoViewModel.addList(button!!.text.toString(),"DEPARTURE")
            }
        }

    }

}