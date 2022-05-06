package com.example.teamdraw.findingteammember

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemRecruitBinding

class RecruitRVAdapter() :
    RecyclerView.Adapter<RecruitRVAdapter.RecruitRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecruitRVViewHolder = RecruitRVViewHolder.from(parent)

    override fun onBindViewHolder(
        holder: RecruitRVViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class RecruitRVViewHolder(binding:ItemRecruitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecruitRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecruitBinding.inflate(layoutInflater, parent, false)
                return RecruitRVViewHolder(binding)
            }
        }

        fun bind(n: Int) {

        }
    }
}