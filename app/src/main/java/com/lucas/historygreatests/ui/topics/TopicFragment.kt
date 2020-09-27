package com.lucas.historygreatests.ui.topics

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lucas.historygreatests.R
import kotlinx.android.synthetic.main.fragment_topic_list.*

/**
 * A fragment representing a list of Items.
 */
class TopicFragment : Fragment() {

    private lateinit var viewModel: TopicsViewModel
    private val topicListAdapter = TopicListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TopicsViewModel::class.java)

        viewModel.loadTopics()

        topicList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = topicListAdapter
        }
    }

}