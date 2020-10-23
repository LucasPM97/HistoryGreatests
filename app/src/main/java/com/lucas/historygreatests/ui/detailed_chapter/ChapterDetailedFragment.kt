package com.lucas.historygreatests.ui.detailed_chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lucas.historygreatests.R
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.ui.components.views.LoadingFullDialog
import com.lucas.historygreatests.utils.extensions.loadFromUrl
import kotlinx.android.synthetic.main.chapter_detailed_fragment.*


class ChapterDetailedFragment : BaseFragment(R.layout.chapter_detailed_fragment) {

    private val viewModel: ChapterDetailedViewModel by viewModels()
    private val args: ChapterDetailedFragmentArgs by navArgs()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.setNavigationOnClickListener {
            activity?.let {
                it.onBackPressed()
            }
        }

        addViewModelObservers()
        setupViewModel()
        viewModel.loadChapter(args.chapterId)
    }


    private fun addViewModelObservers() {
        viewModel.chapter.observe(viewLifecycleOwner, {chapter ->
            collapsing_toolbar.title = chapter.title
            app_bar_image.loadFromUrl(chapter.imageUrl)
            body.text = chapter.body
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it)
                showLoadingDialog()
            else
                dismissLoadingDialog()

        })

        viewModel.errorLoading.observe(viewLifecycleOwner, {
            body.visibility = if (it) View.GONE else View.VISIBLE
            text_error.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupViewModel() {
        viewModel.setup(args)
    }
}