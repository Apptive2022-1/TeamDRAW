package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentInputPositionDetailBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel


class InputPositionDetailFragment : Fragment(), View.OnClickListener {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputPositionDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputPositionDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDetail() // 포지션에 맞는 디테일버튼들 보여주기
        initButtonState() // 초기 버튼들의 상태 결정
    }

    override fun onResume() {
        super.onResume()
        showDetail() // 포지션에 맞는 디테일버튼들 보여주기
    }

    override fun onClick(btn: View?) {
        val button = view?.findViewById<Button>(btn!!.id)
        if (userInfoViewModel.positionDetailList.value!!.contains(button!!.text.toString())) { // 해당 분야를 선택 해제
            btn!!.setBackgroundResource(R.drawable.frame_btn_notselected2)
            userInfoViewModel.removeList(button!!.text.toString(), "POSITION_DETAIL")
        } else { // 해당 분야를 선택
            btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
            userInfoViewModel.addList(button!!.text.toString(), "POSITION_DETAIL")
        }
    }

    private fun showDetail() {
        val positionList = userInfoViewModel.positionList.value
        binding.constraintLayoutDeveloper.visibility = View.GONE
        binding.constraintLayoutDesigner.visibility = View.GONE
        binding.constraintLayoutProductManager.visibility = View.GONE
        if (positionList!!.isEmpty()) {
            binding.constraintLayoutNonePosition.visibility = View.VISIBLE
            return
        }
        binding.constraintLayoutNonePosition.visibility = View.GONE
        for (position in positionList!!) {
            when (position) {
                "개발자" -> {
                    binding.constraintLayoutDeveloper.visibility = View.VISIBLE
                }
                "디자이너" -> {
                    binding.constraintLayoutDesigner.visibility = View.VISIBLE
                }
                "기획자" -> {
                    binding.constraintLayoutProductManager.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initButtonState() {
        val btnProductManager = binding.constraintLayoutProductManager.children
        val btnDesigner = binding.constraintLayoutDesigner.children
        val btnDeveloper = binding.constraintLayoutDeveloper.children

        btnProductManager.forEach { btn ->
            if (btn is Button) {
                btn.setOnClickListener(this) // onClick 오버라이딩한거 적용
                val button = view?.findViewById<Button>(btn!!.id)
                if (userInfoViewModel.positionDetailList.value!!.contains(button!!.text.toString())) { // 해당 포지션 선택됨
                    btn.setBackgroundResource(R.drawable.frame_btn_selected2)
                } else { // 선택되어있지 않음
                    btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
                }
            }
        }
        btnDesigner.forEach { btn ->
            if (btn is Button) {
                btn.setOnClickListener(this)
                val button = view?.findViewById<Button>(btn!!.id)
                if (userInfoViewModel.positionDetailList.value!!.contains(button!!.text.toString())) { // 해당 포지션 선택됨
                    btn.setBackgroundResource(R.drawable.frame_btn_selected2)
                } else { // 선택되어있지 않음
                    btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
                }
            }
        }
        btnDeveloper.forEach { btn ->
            if (btn is Button) {
                btn.setOnClickListener(this)
                val button = view?.findViewById<Button>(btn!!.id)
                if (userInfoViewModel.positionDetailList.value!!.contains(button!!.text.toString())) { // 해당 포지션 선택됨
                    btn.setBackgroundResource(R.drawable.frame_btn_selected2)
                } else { // 선택되어있지 않음
                    btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
                }
            }
        }
    }
}
