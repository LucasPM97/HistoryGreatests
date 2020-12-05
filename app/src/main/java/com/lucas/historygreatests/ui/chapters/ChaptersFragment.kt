package com.lucas.historygreatests.ui.chapters

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
class ChaptersFragment : BaseFragment(R.layout.fragment_list) {

    private val viewModel: ChaptersViewModel by viewModels()
    private val listAdapter = ChapterListAdapter(arrayListOf())

    private val args: ChaptersFragmentArgs by navArgs()

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        viewModel.loadChapters(args.bookId)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            setScrollToBottomListener(5, object : IScrollToBottomListener {
                override fun bottomReached() {
                    viewModel.loadMoreItems(args.bookId)
                }
            })
        }
        implementObservers()
    }

    private fun implementObservers() {
        viewModel.chapters(args.bookId).observe(viewLifecycleOwner, Observer { books ->
            books?.let {
                listAdapter.updateList(it);
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