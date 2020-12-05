package com.lucas.historygreatests.ui.detailed_chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentChapterDetailedBinding
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.utils.extensions.loadFromUrl


class ChapterDetailedFragment : BaseFragment(R.layout.fragment_chapter_detailed) {

    private val viewModel: ChapterDetailedViewModel by viewModels()
    private val args: ChapterDetailedFragmentArgs by navArgs()

    private lateinit var binding: FragmentChapterDetailedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChapterDetailedBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        addViewModelObservers()
    }


    private fun addViewModelObservers() {
        viewModel.chapterDetails(args.chapterId).observe(viewLifecycleOwner, { chapter ->
            chapter?.let {

                if (chapter.body.isEmpty()) viewModel.loadChapter(args.chapterId)

                with(binding) {
                    collapsingToolbar.title = chapter.title
                    appBarImage.loadFromUrl(chapter.imageUrl)
                    body.text = chapter.body
                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            if (it)
                showLoadingDialog()
            else
                dismissLoadingDialog()

        })

        viewModel.errorLoading.observe(viewLifecycleOwner, {
            with(binding) {
                body.visibility = if (it) View.GONE else View.VISIBLE
                textError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}