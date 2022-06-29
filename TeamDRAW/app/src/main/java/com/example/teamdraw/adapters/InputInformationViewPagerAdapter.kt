package com.example.teamdraw.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamdraw.ui.fragments.*

class InputInformationViewPagerAdapter(fm : Fragment) : FragmentStateAdapter(fm) {
    private val PAGENUMBER = 9
    override fun getItemCount(): Int {
        return PAGENUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> InputNameFragment()
            1-> InputLocalFragment()
            2-> InputUnivInfoFragment()
            3-> InputPositionFragment()
            4-> InputPositionDetailFragment()
            5-> InputProfileFragment()
            6 -> InputQuestionFragment()
            7 -> InputSkillsetFragment()
            8 -> InputSkillsetHightoLowFragment()
            else-> InputNameFragment()
        }
    }
}