package com.lucas.historygreatests.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lucas.historygreatests.R
import com.lucas.historygreatests.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_treading.*

class TrendingFragment : BaseFragment(R.layout.fragment_treading) {

    private val viewModel: TrendingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner, Observer {
            text_dashboard.text = it
        })
    }
}