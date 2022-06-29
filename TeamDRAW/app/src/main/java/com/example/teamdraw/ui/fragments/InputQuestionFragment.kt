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
import com.example.teamdraw.databinding.FragmentInputQuestionBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputQuestionFragment : Fragment(), View.OnClickListener{

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputQuestionBinding.inflate(inflater, container, false)
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
        val btnQuestionList = binding.constraintLayoutBtnQuestion.children
        val btnQuestionList1 = binding.constraintLayoutBtnQuestion1.children
        val btnQuestionList2 = binding.constraintLayoutBtnQuestion2.children
        val btnQuestionList3 = binding.constraintLayoutBtnQuestion3.children
        val btnQuestionList4 = binding.constraintLayoutBtnQuestion4.children

        btnQuestionList.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        btnQuestionList1.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        btnQuestionList2.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        btnQuestionList3.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        btnQuestionList4.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }

    }
    private fun btnSetOnClickListener() {
        val btnQuestionList = binding.constraintLayoutBtnQuestion.children
        btnQuestionList.forEach { btn->
            btn.setOnClickListener(this)
        }
        val btnQuestionList1 = binding.constraintLayoutBtnQuestion1.children
        btnQuestionList1.forEach { btn->
            btn.setOnClickListener(this)
        }
        val btnQuestionList2 = binding.constraintLayoutBtnQuestion2.children
        btnQuestionList2.forEach { btn->
            btn.setOnClickListener(this)
        }
        val btnQuestionList3 = binding.constraintLayoutBtnQuestion3.children
        btnQuestionList3.forEach { btn->
            btn.setOnClickListener(this)
        }
        val btnQuestionList4 = binding.constraintLayoutBtnQuestion4.children
        btnQuestionList4.forEach { btn->
            btn.setOnClickListener(this)
        }
    }
    override fun onClick(btn: View?) {
        val button = view?.findViewById<Button>(btn!!.id)
        if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())) {
            btn!!.setBackgroundResource(R.drawable.frame_btn_notselected2)
            userInfoViewModel.removeList(button!!.text.toString(), "QUESTION")
        } else {
            btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
            userInfoViewModel.addList(button!!.text.toString(), "QUESTION")
        }
    }

}