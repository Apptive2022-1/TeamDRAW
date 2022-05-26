package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.GmailSender
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentInputUnivInfoBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputUnivInfoFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputUnivInfoBinding
    var authenticationCode :String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInputUnivInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.user = userInfoViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUnivInfo()
        initEmailAuthenticateButton() // 이메일 인증 버튼

        // 인증메일 전송 버튼
        binding.btnSendAuthenticationEmail.setOnClickListener {
            val toemail = binding.etvInputUnivEmail.text.toString()
            if( toemail.contains("ac.kr") ){ // 도메인이 학교 도메인인 경우
                val sender = GmailSender()
                sender.sendEmail(toemail) // 이메일 보내기
                authenticationCode = sender.authenticationCode
                binding.linearlayoutAuthenticationCode.visibility = View.VISIBLE
                binding.btnSendAuthenticationEmail.text = "재전송하기"
            }
            else {
                Toast.makeText(context, "이메일이 ac.kr로 끝나야합니다 !", Toast.LENGTH_SHORT).show()
            }
        }

        // 인증코드 확인 버튼
        binding.btnCompareAuthenticationCode.setOnClickListener{
            if(authenticationCode == binding.etvInputAuthenticationCode.text.toString()){ // 코드가 일치하면
                userInfoViewModel.isEmailAuthenticated.value = "true"
                binding.btnSendAuthenticationEmail.text = "인증완료"
                binding.btnSendAuthenticationEmail.isEnabled = false // 버튼막기
                binding.linearlayoutAuthenticationCode.visibility = View.GONE
            }
            else{
                Toast.makeText(context, "인증에 실패했습니다. 정확하게 입력해주세요.", Toast.LENGTH_SHORT).show()
                Log.d("인증코드 ", "$authenticationCode  ${binding.etvInputAuthenticationCode.text}")
            }
        }
    }

    private fun initEmailAuthenticateButton() {
        Log.d("email : ", userInfoViewModel.isEmailAuthenticated.value.toString())
        when (userInfoViewModel.isEmailAuthenticated.value) {
            "true" -> { // 이미 인증 했으면 버튼 막음
                binding.btnSendAuthenticationEmail.text = "인증완료"
                binding.btnSendAuthenticationEmail.isEnabled = false // 버튼막기
                binding.linearlayoutAuthenticationCode.visibility = View.GONE
            }

        }
    }

    fun updateUnivInfo() {
        binding.etvInputUnivName.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivName.text.toString(), "UNIV")
        }
        binding.etvInputUnivEmail.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivEmail.text.toString(), "UNIV_EMAIL")
        }
        binding.etvInputUnivMajor.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivMajor.text.toString(), "MAJOR")
        }

        binding.btnGrade.setOnClickListener {
            var popMenu = PopupMenu(context, it)
            popMenu.menuInflater.inflate(R.menu.grade_popup_menu, popMenu.menu)
            popMenu.show()
            popMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.freshmen -> binding.btnGrade.text = "1학년"
                    R.id.sophomore -> binding.btnGrade.text = "2학년"
                    R.id.junior -> binding.btnGrade.text = "3학년"
                    R.id.senior -> binding.btnGrade.text = "4학년"
                    R.id.graduate -> binding.btnGrade.text = "대학원"
                }
                return@setOnMenuItemClickListener false
            }
            popMenu.setOnDismissListener {
                userInfoViewModel.updateValue(binding.btnGrade.text.toString(), "GRADE")
                Log.d(
                    "Grade Popup Menu updates viewModel ",
                    userInfoViewModel.grade?.value.toString()
                )
            }
        }
    }


}