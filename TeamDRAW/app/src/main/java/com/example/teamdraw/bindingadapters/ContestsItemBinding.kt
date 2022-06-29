package com.example.teamdraw.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.teamdraw.R
import com.example.teamdraw.models.Contest
import com.example.teamdraw.ui.fragments.ContestFragmentDirections
import java.lang.Exception

class ContestsItemBinding {
    companion object {
        @BindingAdapter("onContestClickListener")
        @JvmStatic
        fun onContestClickListener(contestItemCardView: CardView, contest: Contest) {
            contestItemCardView.setOnClickListener {
                try {
                    val action =
                        ContestFragmentDirections.actionContestFragmentToContestDetailFragment(
                            contest
                        )
                    it.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onContestClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("bindingImage")
        @JvmStatic
        fun bindingImage(view: ImageView, contest: Contest) {
            when (contest.contestId) {
                0L -> {Glide.with(view.context).load(R.drawable.ct).into(view)}
                1L -> {Glide.with(view.context).load(R.drawable.ct1).into(view)}
                2L -> {Glide.with(view.context).load(R.drawable.ct2).into(view)}
                3L -> {Glide.with(view.context).load(R.drawable.ct3).into(view)}
                4L -> {Glide.with(view.context).load(R.drawable.ac).into(view)}
                5L -> {Glide.with(view.context).load(R.drawable.ac1).into(view)}
                6L -> {Glide.with(view.context).load(R.drawable.ac2).into(view)}
                7L -> {Glide.with(view.context).load(R.drawable.ac3).into(view)}
            }
        }
    }
}