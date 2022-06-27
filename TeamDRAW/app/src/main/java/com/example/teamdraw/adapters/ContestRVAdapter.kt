package com.example.teamdraw.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.data.database.ContestsEntity
import com.example.teamdraw.databinding.ItemContestRvBinding
import com.example.teamdraw.models.Contest


// 리사이클러뷰 어댑터
class ContestRVAdapter() :
    RecyclerView.Adapter<ContestRVAdapter.ContestRVViewHolder>() {

    private var list = listOf<ContestsEntity>()

    fun setList(nlist: List<ContestsEntity>) {
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
        val contest = list[position].contest
        holder.bind(contest)
    }

    override fun getItemCount(): Int = list.size

    class ContestRVViewHolder(
        private val binding: ItemContestRvBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ContestRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContestRvBinding.inflate(layoutInflater, parent, false)
                return ContestRVViewHolder(binding)
            }
        }

        fun bind(item: Contest) {
            Log.d("##12", "ContestRVViewHolder - bind() called")
            binding.contest = item
            binding.executePendingBindings()
        }
    }
}