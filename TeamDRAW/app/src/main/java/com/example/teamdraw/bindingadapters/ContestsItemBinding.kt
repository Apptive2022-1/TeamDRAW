package com.example.teamdraw.bindingadapters

import android.util.Log
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.teamdraw.models.Contest
import com.example.teamdraw.ui.fragments.ContestFragmentDirections
import java.lang.Exception

class ContestsItemBinding {
    companion object{
        @BindingAdapter("onContestClickListener")
        @JvmStatic
        fun onContestClickListener(contestItemCardView: CardView, contest: Contest){
            contestItemCardView.setOnClickListener {
                try {
                    val action = ContestFragmentDirections.actionContestFragmentToContestDetailFragment(contest)
                    it.findNavController().navigate(action)
                }catch (e:Exception){
                    Log.d("onContestClickListener", e.toString())
                }
            }
        }
    }
}