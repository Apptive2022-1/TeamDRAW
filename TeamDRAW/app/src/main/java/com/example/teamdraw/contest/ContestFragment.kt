package com.example.teamdraw.contest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentContestBinding
import java.nio.file.DirectoryIteratorException


class ContestFragment : Fragment() {

    lateinit var binding: FragmentContestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContestBinding.inflate(inflater,container,false)

        val adapter = ContestRVAdapter(MyClickListener {
            findNavController().navigate(ContestFragmentDirections.actionContestFragmentToContestDetailFragment())
            Log.d("##12", "ContestFragment - onCreateView() called")
        })
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