package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragementInputSkillsetBinding
import com.example.teamdraw.databinding.FragmentInputProfileBinding
import com.example.teamdraw.databinding.FragmentInputQuestionBinding

import com.example.teamdraw.databinding.FragmentInputSkillsethightolowBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputSkillsetHightoLowFragment : Fragment(), View.OnClickListener {

    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputSkillsethightolowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInputSkillsethightolowBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
    }

    private fun initButton() {
        val skill = binding.constraintLayoutSkillLL.children

        skill.forEach { btn->
            val button = view?.findViewById<Button>(btn!!.id)
            if (userInfoViewModel.skillH.value!! == button!!.text.toString()){ // 해당 분야를 선택안됨
                btn.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else { // 해당 분야를 선택
                btn.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }



    }

    override fun onClick(p0: View?) {

    }
    /**private fun btnSetOnClickListener(view: View, btn: View?) {
    if (view is RadioButton) {
    val button = view?.findViewById<Button>(btn!!.id)
    val checked = view.isChecked
    // Check which radio button was clicked
    when (view.getId()) {
    R.id.skill1 ->
    if (checked) {
    btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
    userInfoViewModel.updateValue(button!!.text.toString(), "SKILLH")
    }
    R.id.skill2 ->
    if (checked) {
    btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
    userInfoViewModel.updateValue(button!!.text.toString(), "SKILLM")
    }
    R.id.skill3 ->
    if (checked) {
    btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
    userInfoViewModel.updateValue(button!!.text.toString(), "SKILLL")
    }
    }
    }
    override fun onClick(btn: View?) {
    val button = view?.findViewById<Button>(btn!!.id)
    if (userInfoViewModel.questionList.value!!.contains(button!!.text.toString())) {
    btn!!.setBackgroundResource(R.drawable.frame_btn_notselected2)
    userInfoViewModel.removeList(button!!.text.toString(), "QUESTION")
    } else {
    btn!!.setBackgroundResource(R.drawable.frame_btn_selected2)
    userInfoViewModel.updateValue(button!!.text.toString(), "SKILLL")
    }
    }**/
}