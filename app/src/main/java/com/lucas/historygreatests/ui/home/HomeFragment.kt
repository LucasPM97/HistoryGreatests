package com.lucas.historygreatests.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentListBinding
import com.lucas.historygreatests.ui.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_list) {

    private val viewModel: HomeViewModel by viewModels()
    private val topicListAdapter = TopicListAdapter(arrayListOf())

    private lateinit var binding: FragmentListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        viewModel.loadTopics()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topicListAdapter
        }

        implementObservers()
    }

    private fun implementObservers() {
        viewModel.topics.observe(viewLifecycleOwner, Observer { topics ->
            topics?.let {
                topicListAdapter.updateList(it);
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (loading)
                showLoadingDialog()
            else
                dismissLoadingDialog()
        })

        viewModel.errorLoading.observe(viewLifecycleOwner, { error ->
            binding.textError.visibility = if (error) View.VISIBLE else View.GONE
        })
    }
}