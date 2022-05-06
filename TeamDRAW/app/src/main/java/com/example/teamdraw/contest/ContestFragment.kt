package com.example.teamdraw.contest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentContestBinding


class ContestFragment : Fragment() {

    lateinit var binding: FragmentContestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contest, container, false)
//        binding = FragmentContestBinding.inflate(inflater,container,false)

        val adapter = ContestRVAdapter()
        adapter.setList(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        binding.contestRv.adapter = adapter
        binding.contestRv.layoutManager = GridLayoutManager(context, 2)

        val viewSpinner = binding.viewSpinner
        ArrayAdapter.createFromResource(
            requireNotNull(context),
            R.array.spinner_view_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            viewSpinner.adapter = adapter
        }

        val sortSpinner = binding.sortSpinner
        ArrayAdapter.createFromResource(
            requireNotNull(context),
            R.array.spinner_sort_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortSpinner.adapter = adapter
        }

        return binding.root
    }
}