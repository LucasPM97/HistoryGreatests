package com.lucas.historygreatests.ui.detailed_chapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lucas.historygreatests.R
import com.lucas.historygreatests.databinding.FragmentChapterDetailedBinding
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.utils.extensions.loadFromUrl


class ChapterDetailedFragment : BaseFragment(R.layout.fragment_chapter_detailed) {

    private val viewModel: ChapterDetailedViewModel by viewModels()
    private val args: ChapterDetailedFragmentArgs by navArgs()

    private lateinit var binding: FragmentChapterDetailedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActivityToolbarVisibility(false)
        binding = FragmentChapterDetailedBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
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
            with(binding){
                collapsingToolbar.title = chapter.title
                appBarImage.loadFromUrl(chapter.imageUrl)
                body.text = chapter.body
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

    private fun setupViewModel() {
        viewModel.setup(args)
    }
}