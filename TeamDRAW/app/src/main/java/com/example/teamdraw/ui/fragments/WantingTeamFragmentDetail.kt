package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.WantingRVAdapter
import com.example.teamdraw.databinding.FragmentWantingTeamDetailBinding

class WantingTeamFragmentDetail : Fragment() {

    private val args by navArgs<WantingTeamFragmentDetailArgs>()
    lateinit var binding : FragmentWantingTeamDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =  FragmentWantingTeamDetailBinding.inflate(inflater, container, false)

        val filter = args.filter
        val wantingAdapter = WantingRVAdapter()

        wantingAdapter.setList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))

        binding.wantingRv.adapter = wantingAdapter
        binding.wantingRv.layoutManager = GridLayoutManager(context, 3)

        val filterSpinner = binding.filterSpinner

        val adapter = ArrayAdapter.createFromResource(
            requireNotNull(context),
            R.array.spinner_wanting_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        filterSpinner.adapter = adapter

        when(filter){
            "me" -> filterSpinner.setSelection(0)
            "different" -> filterSpinner.setSelection(1)
            "same" -> filterSpinner.setSelection(2)
        }

        return binding.root
    }

}