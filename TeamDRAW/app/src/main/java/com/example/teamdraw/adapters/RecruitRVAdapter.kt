package com.example.teamdraw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemRecruitBinding

class RecruitRVAdapter(var mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RecruitRVAdapter.RecruitRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecruitRVViewHolder = RecruitRVViewHolder.from(parent, mItemClickListener)

    override fun onBindViewHolder(
        holder: RecruitRVViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    class RecruitRVViewHolder(val binding: ItemRecruitBinding, private val mItemClickListener : ItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, mItemClickListener: ItemClickListener): RecruitRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecruitBinding.inflate(layoutInflater, parent, false)
                return RecruitRVViewHolder(binding, mItemClickListener)
            }
        }

        init {
            itemView.setOnClickListener { mItemClickListener.onClick() }
        }

        fun bind(n: Int) {

        }
    }

    interface ItemClickListener {
        fun onClick()
    }
}