package com.lucas.historygreatests.ui.chapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucas.historygreatests.R
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A fragment representing a list of Items.
 */
class ChaptersFragment : Fragment() {

    private lateinit var viewModel: ChaptersViewModel
    private val listAdapter = ChapterListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ChaptersViewModel::class.java)

        viewModel.loadBooks()

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
        implementObservers()
    }

    private fun implementObservers() {
        viewModel.chapters.observe(this, Observer { books ->
            books?.let{
                listAdapter.updateList(it);
            }
        })

        viewModel.loadingError.observe(this,{error ->
            text_error.visibility = if(error) View.VISIBLE else View.GONE
        })
    }

}