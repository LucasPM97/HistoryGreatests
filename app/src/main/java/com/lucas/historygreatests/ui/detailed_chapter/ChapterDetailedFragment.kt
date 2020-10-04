package com.lucas.historygreatests.ui.detailed_chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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
        viewModel.chapter.observe(this, {
            collapsing_toolbar.title = it.title
            app_bar_image.loadFromUrl(it.imageUrl)
            body.text = it.body
        })
        viewModel.loading.observe(this, {
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

        viewModel.errorLoading.observe(this, {
            body.visibility = if (it) View.GONE else View.VISIBLE
            text_error.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupViewModel() {
        viewModel.setup(args)
    }
}