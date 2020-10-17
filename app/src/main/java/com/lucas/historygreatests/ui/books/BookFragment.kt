package com.lucas.historygreatests.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucas.historygreatests.R
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.ui.detailed_chapter.ChapterDetailedFragmentArgs
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A fragment representing a list of Items.
 */
class BookFragment : BaseFragment() {

    private val viewModel: BooksViewModel by viewModels()
    private val listAdapter = BookListAdapter(arrayListOf())

    private val args: BookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadBooks(args.topicId)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        implementObservers()
    }

    private fun implementObservers() {
        viewModel.books.observe(viewLifecycleOwner, Observer { books ->
            books?.let{
                listAdapter.updateList(it);
            }
        })

        viewModel.errorLoading.observe(viewLifecycleOwner,{error ->
            text_error.visibility = if(error) View.VISIBLE else View.GONE
        })
    }

}