package com.lucas.historygreatests.ui.detailed_chapter

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lucas.historygreatests.models.viewModels.BaseViewModel
import com.lucas.historygreatests.models.Chapter
import com.lucas.historygreatests.services.FirestoreDocumentCallback
import com.lucas.historygreatests.services.chapters.FirestoreChaptersService

class ChapterDetailedViewModel(application: Application) : BaseViewModel(application), IChapterDetailedViewModel {

    override val chapter = MutableLiveData<Chapter>()
    override val firestoreService = FirestoreChaptersService()

    override fun setup(args: ChapterDetailedFragmentArgs) {
        args.apply {
            chapter.value = Chapter(
                chapter_id= chapterId,
                title= title,
                description = "",
                imageUrl = imageUrl,
                imageColor= imageColor,
                startYear= startYear,
                endYear= endYear ?: "",
            )
        }

    }

    override fun loadChapter(chapterId:String){
        errorLoading.value = false
        loading.value = true

        firestoreService.getDetailedChapterById(chapterId,object : FirestoreDocumentCallback<Chapter> {
            override fun onSuccess(result: Chapter?) {
                if (result != null)
                    chapter.value = result
                else
                    errorLoading.value = true

            }

            override fun onFailed(exception: Exception) {
                errorLoading.value = true
            }

            override fun onCompleted() {
                loading.value = false
            }

        })
    }

}