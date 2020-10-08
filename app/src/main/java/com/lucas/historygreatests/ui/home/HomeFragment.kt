package com.lucas.historygreatests.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel
import com.lucas.historygreatests.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val topicListAdapter = TopicListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadTopics()

        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topicListAdapter
        }

        implementObservers()
    }

    private fun implementObservers() {
        viewModel.topics.observe(viewLifecycleOwner, Observer { topics ->
            topics?.let{
                topicListAdapter.updateList(it);
            }
        })

        viewModel.errorLoading.observe(viewLifecycleOwner,{error ->
            text_error.visibility = if(error) View.VISIBLE else View.GONE
        })
    }
}