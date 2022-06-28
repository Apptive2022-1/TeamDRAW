package com.example.teamdraw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.data.database.UserEntity
import com.example.teamdraw.databinding.ItemWantingBinding
import com.example.teamdraw.models.User
import com.example.teamdraw.util.FragmentLocation

class WantingRVAdapter(val location: FragmentLocation) : RecyclerView.Adapter<WantingRVAdapter.WantingRVViewHolder>() {

    private var list = listOf<UserEntity>()

    fun setList(nlist: List<UserEntity>) {
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
        holder.bind(list[position].user, location)
    }

    override fun getItemCount(): Int = list.size

    class WantingRVViewHolder(val binding: ItemWantingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): WantingRVViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWantingBinding.inflate(layoutInflater, parent, false)
                return WantingRVViewHolder(binding)
            }
        }

        fun bind(user: User, location: FragmentLocation) {
            binding.user = user
            binding.location = location
            binding.executePendingBindings()
        }
    }
}