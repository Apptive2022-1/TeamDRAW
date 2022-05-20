package com.example.teamdraw.contest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemContestRvBinding


// 리사이클러뷰 어댑터
class ContestRVAdapter(private val clickListener: MyClickListener) :
    RecyclerView.Adapter<ContestRVAdapter.ContestRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContestRVViewHolder = ContestRVViewHolder.from(parent, clickListener)

    override fun onBindViewHolder(
        holder: ContestRVViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    class ContestRVViewHolder(
        private val binding: ItemContestRvBinding,
        private val clickListener: MyClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, clickListener: MyClickListener): ContestRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemContestRvBinding.inflate(layoutInflater, parent, false)
                return ContestRVViewHolder(binding, clickListener)
            }
        }

        fun bind() {
            Log.d("##12", "ContestRVViewHolder - bind() called")
            binding.listener = clickListener
            binding.executePendingBindings()
        }
    }
}

class MyClickListener(private val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}