package com.example.teamdraw.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.teamdraw.ui.fragments.*

class TeamListViewPagerAdapter(private val teamFragmentList: ArrayList<Fragment>, fm: Fragment ) : FragmentStateAdapter(fm) {
    private val fmIds = teamFragmentList.map{it.hashCode().toLong()}
    override fun getItemCount(): Int {
        return teamFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return teamFragmentList[position]

    }
    override fun getItemId(position: Int): Long {
        return teamFragmentList[position].hashCode().toLong()
    }
    override fun containsItem(itemId: Long): Boolean {
        return fmIds.contains(itemId)
    }
}