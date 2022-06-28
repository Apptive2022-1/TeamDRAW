package com.example.teamdraw.bindingadapters

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.teamdraw.models.User
import com.example.teamdraw.ui.fragments.ContestFragmentDirections
import com.example.teamdraw.ui.fragments.FindingTeamMembersFragmentDirections
import com.example.teamdraw.ui.fragments.WantingTeamFragmentDetailDirections
import com.example.teamdraw.ui.fragments.WantingTeamFragmentDirections
import com.example.teamdraw.util.FragmentLocation
import java.lang.Exception

class UserItemBinding {
    companion object {

        @BindingAdapter("onUserClick", "location")
        @JvmStatic
        fun onUserClickListener(userView: View, user: User, location: FragmentLocation) {
            userView.setOnClickListener {
                try {
                    when (location) {
                        FragmentLocation.Finding -> {
                            val action =
                                FindingTeamMembersFragmentDirections.actionFindingTeamMembersFragmentToUserProfileFragment(
                                    user
                                )
                            it.findNavController().navigate(action)
                        }
                        FragmentLocation.Wanting -> {
                            val action =
                                WantingTeamFragmentDirections.actionWantingTeamFragmentToUserProfileFragment(
                                    user
                                )
                            it.findNavController().navigate(action)
                        }
                        FragmentLocation.WantingDetail -> {
                            val action =
                                WantingTeamFragmentDetailDirections.actionWantingTeamFragmentDetailToUserProfileFragment(
                                    user
                                )
                            it.findNavController().navigate(action)
                        }

                    }

                } catch (e: Exception) {
                    Log.d("onContestClickListener", e.toString())
                }
            }
        }

    }
}