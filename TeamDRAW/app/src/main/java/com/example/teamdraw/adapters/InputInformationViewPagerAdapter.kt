package com.example.teamdraw.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamdraw.ui.fragments.InputLocalFragment
import com.example.teamdraw.ui.fragments.InputNameFragment
import com.example.teamdraw.ui.fragments.InputPositionFragment
import com.example.teamdraw.ui.fragments.InputUnivInfoFragment

class InputInformationViewPagerAdapter(fm : Fragment) : FragmentStateAdapter(fm) {
    private val PAGENUMBER = 4
    override fun getItemCount(): Int {
        return PAGENUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> InputNameFragment()
            1-> InputLocalFragment()
            2-> InputUnivInfoFragment()
            3-> InputPositionFragment()
            else-> InputNameFragment()
        }
    }
}