package com.lucas.historygreatests.ui.trending

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentTreadingBinding
import com.lucas.historygreatests.ui.BaseFragment

class TrendingFragment : BaseFragment(R.layout.fragment_treading) {

    private val viewModel: TrendingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTreadingBinding.bind(view)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textDashboard.text = it
        })
    }
}