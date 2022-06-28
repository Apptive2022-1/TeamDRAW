package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.R
import com.example.teamdraw.adapters.WantingRVAdapter
import com.example.teamdraw.databinding.FragmentWantingTeamDetailBinding
import com.example.teamdraw.util.FragmentLocation
import com.example.teamdraw.viewmodels.FindingTeamViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WantingTeamFragmentDetail : Fragment() {

    private lateinit var wantingAdapter: WantingRVAdapter
    private val viewModel: FindingTeamViewModel by viewModels()
    private val args by navArgs<WantingTeamFragmentDetailArgs>()
    lateinit var binding: FragmentWantingTeamDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWantingTeamDetailBinding.inflate(inflater, container, false)

        val filter = args.filter

        readDatabase()

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

        when (filter) {
            "me" -> filterSpinner.setSelection(0)
            "different" -> filterSpinner.setSelection(1)
            "same" -> filterSpinner.setSelection(2)
        }

        return binding.root
    }

    private fun readDatabase() {
        wantingAdapter = WantingRVAdapter(FragmentLocation.WantingDetail)
        lifecycleScope.launch {
            viewModel.readUsers.observe(viewLifecycleOwner) { uesrs ->
                if (uesrs.isNotEmpty()) {
                    wantingAdapter.setList(uesrs)
                }
            }
        }
    }
}