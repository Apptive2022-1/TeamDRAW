package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentInputUnivInfoBinding
import com.example.teamdraw.viewmodels.UserInfoViewModel

class InputUnivInfoFragment : Fragment() {
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private lateinit var binding: FragmentInputUnivInfoBinding
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


    }
    fun updateUnivInfo(){
        binding.etvInputUnivName.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivName.text.toString(), "UNIV")
        }
        binding.etvInputUnivEmail.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivEmail.text.toString(), "UNIV_EMAIL")
        }
        binding.etvInputUnivMajor.doAfterTextChanged {
            userInfoViewModel.updateValue(binding.etvInputUnivMajor.text.toString(), "MAJOR")
        }

        binding.btnGrade.setOnClickListener{
            var popMenu = PopupMenu(context, it)
            popMenu.menuInflater.inflate(R.menu.grade_popup_menu, popMenu.menu)
            popMenu.show()
            popMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.freshmen ->  binding.btnGrade.text = "1학년"
                    R.id.sophomore-> binding.btnGrade.text = "2학년"
                    R.id.junior-> binding.btnGrade.text = "3학년"
                    R.id.senior-> binding.btnGrade.text = "4학년"
                    R.id.graduate-> binding.btnGrade.text = "대학원"
                }
                return@setOnMenuItemClickListener false
            }
            popMenu.setOnDismissListener{
                userInfoViewModel.updateValue(binding.btnGrade.text.toString(),"GRADE")
                Log.d("Grade Popup Menu updates viewModel ", userInfoViewModel.grade?.value.toString())
            }
        }
    }


}