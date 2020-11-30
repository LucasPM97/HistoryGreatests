package com.lucas.historygreatests.ui.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentListBinding
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.utils.extensions.IScrollToBottomListener
import com.lucas.historygreatests.utils.extensions.setScrollToBottomListener

/**
 * A fragment representing a list of Items.
 */
class BookFragment : BaseFragment(R.layout.fragment_list) {

    private val viewModel: BooksViewModel by viewModels()
    private val listAdapter = BookListAdapter(arrayListOf())

    private val args: BookFragmentArgs by navArgs()

    private lateinit var viewBinding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentListBinding.bind(view)

        viewModel.loadBooks(args.topicId)

        viewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setScrollToBottomListener(5, object : IScrollToBottomListener {
                override fun bottomReached() {
                    viewModel.loadMoreItems(args.topicId)
                }
            })
        }

        implementObservers()
    }

    private fun implementObservers() {
        viewModel.books(args.topicId).observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                listAdapter.updateList(it);
            }
        })

        viewModel.errorLoading.observe(viewLifecycleOwner, { error ->
            viewBinding.textError.visibility = if (error) View.VISIBLE else View.GONE
        })
    }

}