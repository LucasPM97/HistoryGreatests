package com.lucas.historygreatests.ui.detailed_chapter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lucas.historygreatests.R
import com.lucas.historygreatests.utils.loadFromUrl
import kotlinx.android.synthetic.main.chapter_detailed_fragment.*

class ChapterDetailedFragment : Fragment() {

    private lateinit var viewModel: ChapterDetailedViewModel
    val args: ChapterDetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chapter_detailed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChapterDetailedViewModel::class.java)
        addViewModelObservers()
        setupViewModel()
        viewModel.loadChapter()
    }


    private fun addViewModelObservers() {
        viewModel.chapter.observe(this, {
            collapseable_toolbar.title = it.title
            app_bar_image.loadFromUrl(it.imageUrl)
            body.text = it.body
        })
    }

    private fun setupViewModel() {
        viewModel.setup(args)
    }
}