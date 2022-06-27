package com.example.teamdraw.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.teamdraw.databinding.ItemRecruitBinding
import com.example.teamdraw.models.Recruiting

class RecruitRVAdapter(var mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<RecruitRVAdapter.RecruitRVViewHolder>() {

    private var list = mutableListOf<Recruiting>()

    fun setList(nlist : MutableList<Recruiting>) {
        list = nlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecruitRVViewHolder = RecruitRVViewHolder.from(parent, mItemClickListener)

    override fun onBindViewHolder(holder: RecruitRVViewHolder, position: Int) {
        holder.bind(list[position], position)
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

        fun bind(rc: Recruiting, n:Int) {
            binding.tvRecruitingText.text = rc.text
            binding.tvRecruitingTitle.text = rc.title
            binding.cs.setOnClickListener {
                Log.d("test","test")
                mItemClickListener.onClick(n)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(n: Int)
    }
}