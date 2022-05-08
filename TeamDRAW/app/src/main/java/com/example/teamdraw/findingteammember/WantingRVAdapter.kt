package com.example.teamdraw.findingteammember

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemWantingBinding

class WantingRVAdapter : RecyclerView.Adapter<WantingRVAdapter.WantingRVViewHolder>() {

    private var list = listOf<Int>()

    fun setList(nlist: List<Int>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WantingRVViewHolder = WantingRVViewHolder.from(parent)

    override fun onBindViewHolder(
        holder: WantingRVViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class WantingRVViewHolder(binding: ItemWantingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): WantingRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWantingBinding.inflate(layoutInflater, parent, false)
                return WantingRVViewHolder(binding)
            }
        }

        fun bind(n: Int) {

        }
    }
}