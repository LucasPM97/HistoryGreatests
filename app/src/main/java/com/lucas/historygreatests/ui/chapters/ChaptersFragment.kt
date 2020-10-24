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
            binding.textError.visibility = if(error) View.VISIBLE else View.GONE
        })
    }

}