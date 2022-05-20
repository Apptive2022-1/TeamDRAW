package com.example.teamdraw.inputinformation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class InputInformationViewPagerAdapter(fm : Fragment) : FragmentStateAdapter(fm) {
    private val PAGENUMBER = 3
    override fun getItemCount(): Int {
        return PAGENUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->InputNameFragment()
            1->InputLocalFragment()
            2->InputUnivInfoFragment()
            else->InputNameFragment()
        }
    }
}