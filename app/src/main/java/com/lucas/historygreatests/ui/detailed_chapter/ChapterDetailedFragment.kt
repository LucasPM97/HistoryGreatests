package com.lucas.historygreatests.ui.detailed_chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.lucas.historygreatests.R
import com.lucas.historygreatests.ui.BaseFragment
import com.lucas.historygreatests.ui.components.views.LoadingFullDialog
import com.lucas.historygreatests.utils.AuthenticationManager
import com.lucas.historygreatests.utils.loadFromUrl
import kotlinx.android.synthetic.main.chapter_detailed_fragment.*


class ChapterDetailedFragment : BaseFragment() {

    private val viewModel: ChapterDetailedViewModel by viewModels()
    private val args: ChapterDetailedFragmentArgs by navArgs()

    private lateinit var loadingDialog: LoadingFullDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chapter_detailed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.setNavigationOnClickListener {
            activity?.let {
                it.onBackPressed()
            }
        }

        addViewModelObservers()
        setupViewModel()
        viewModel.loadChapter()
    }


    private fun addViewModelObservers() {
        viewModel.chapter.observe(viewLifecycleOwner, {
            collapsing_toolbar.title = it.title
            app_bar_image.loadFromUrl(it.imageUrl)
            body.text = it.body
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            //TODO: Test loading Dialog
            /*if (it){
                context?.let { context ->
                    LoadingFullDialog.showLoadingDialog(context)
                }
            }
            else{
                LoadingFullDialog.dissmisLoadingDialog()
            }*/

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