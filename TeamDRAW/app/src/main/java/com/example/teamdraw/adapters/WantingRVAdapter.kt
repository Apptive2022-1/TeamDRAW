package com.example.teamdraw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemWantingBinding

class WantingRVAdapter(var mItemClickListener: ItemClickListener) : RecyclerView.Adapter<WantingRVAdapter.WantingRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WantingRVViewHolder = WantingRVViewHolder.from(parent, mItemClickListener)

    override fun onBindViewHolder(
        holder: WantingRVViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class WantingRVViewHolder(val binding: ItemWantingBinding, private val mItemClickListener: ItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, mItemClickListener: ItemClickListener): WantingRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWantingBinding.inflate(layoutInflater, parent, false)
                return WantingRVViewHolder(binding, mItemClickListener)
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