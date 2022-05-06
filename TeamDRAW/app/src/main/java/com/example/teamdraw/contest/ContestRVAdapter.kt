package com.example.teamdraw.contest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemContestRvBinding


// 리사이클러뷰 어댑터
class ContestRVAdapter() :
    RecyclerView.Adapter<ContestRVAdapter.ContestRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContestRVViewHolder = ContestRVViewHolder.from(parent)

    override fun onBindViewHolder(
        holder: ContestRVViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ContestRVViewHolder(binding: ItemContestRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ContestRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContestRvBinding.inflate(layoutInflater, parent, false)
                return ContestRVViewHolder(binding)
            }
        }

        fun bind(n: Int) {

        }
    }
}