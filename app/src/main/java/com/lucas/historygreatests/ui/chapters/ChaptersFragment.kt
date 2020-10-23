package com.lucas.historygreatests.ui.chapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucas.historygreatests.R
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.ui.books.BookFragmentArgs
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A fragment representing a list of Items.
 */
class ChaptersFragment : BaseFragment(R.layout.fragment_list) {

    private val viewModel: ChaptersViewModel by viewModels()
    private val listAdapter = ChapterListAdapter(arrayListOf())

    private val args: ChaptersFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadChapters(args.bookId)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        implementObservers()
    }

    private fun implementObservers() {
        viewModel.chapters.observe(viewLifecycleOwner, Observer { books ->
            books?.let{
                listAdapter.updateList(it);
            }
        })

        viewModel.errorLoading.observe(viewLifecycleOwner,{error ->
            text_error.visibility = if(error) View.VISIBLE else View.GONE
        })
    }

}