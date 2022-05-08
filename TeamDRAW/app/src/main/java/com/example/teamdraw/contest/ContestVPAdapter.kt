package com.example.teamdraw.contest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemContestVpBinding


/**
 *  뷰홀더 어댑터
 * 공모전 프래그먼트 구성시 뷰페이저 리사이클러뷰 중복해서 사용??
 * 아니면 app Bar 생성하고 fragmentContainer 생성?
 */
class ContestVPAdapter() :
    RecyclerView.Adapter<ContestVPAdapter.ContestVPViewHolder>() {

    var fragmentList = mutableListOf<RecyclerView>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestVPViewHolder {
        return ContestVPViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ContestVPViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ContestVPViewHolder(binding: ItemContestVpBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup):ContestVPViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContestVpBinding.inflate(layoutInflater, parent, false)
                return ContestVPViewHolder(binding)
            }
        }

    }
}